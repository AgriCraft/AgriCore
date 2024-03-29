package com.agricraft.agricore.util;

import com.agricraft.agricore.core.AgriCore;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.reflections.Reflections;
import org.reflections.scanners.Scanners;
import org.reflections.util.ConfigurationBuilder;

public class ResourceHelper {
    /**
     * Copies all resources in the class path matching the given name predicate, as well as the file structure predicate to a new directory
     *
     * @param pathStream stream of all paths to scan
     * @param nameFilter the predicate for the file name
     * @param dirFilter the predicate for the file (directory) structure
     * @param toFunction function specifying the destination where each file should be copied to
     * @param overwrite specifies if files which already exist should be overwritten or not
     */
    public static void copyResources(Stream<Path> pathStream, Pattern nameFilter, Predicate<String> dirFilter, Function<String, Path> toFunction, boolean overwrite) {
        ResourceHelper helper = new ResourceHelper(collectURLs(pathStream));
        helper.findResources(nameFilter).stream()
                .filter(dirFilter)
                .forEach(r -> helper.copyResource(r, toFunction.apply(r), overwrite));
    }

    /**
     * Collects URLs from a stream of paths
     * Will catch Exceptions and log them in case of an invalid path
     *
     * @param pathStream stream of all paths to scan
     * @return a collection of URLs representing the paths (except the invalid ones)
     */
    private static Collection<URL> collectURLs(Stream<Path> pathStream) {
        return pathStream.map(Path::toUri)
                .map(uri -> {
                    URL url = null;
                    try {
                        url = uri.toURL();
                    } catch (MalformedURLException e) {
                        AgriCore.getLogger("AgriCraft").error("Unable to scan path");
                        AgriCore.getLogger("AgriCraft").trace(e);
                    }
                    return url;
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    /**
     * Reflections instance used to find jsons
     */
    private final Reflections reflections;

    /**
     * The Reflections object can use quite some memory, therefore we instantiate a new one when we need it,
     * and discard it afterwards
     */
    protected ResourceHelper(Collection<URL> urls) {
        // Prevent logging from Reflections
        if(Reflections.log != null) {
            //Reflections.log = null;
        }
        // Scan using Reflections
        this.reflections = new Reflections(
                new ConfigurationBuilder()
                        .addScanners(Scanners.Resources)
                        .addUrls(urls));
    }

    /**
     * Finds all resources in the class path matching the given predicate
     * @param nameFilter file name predicate
     * @return set of all filenames matching the name filter
     */
    protected Set<String> findResources(Pattern nameFilter) {
        return this.reflections.getResources(nameFilter);
    }

    /**
     * Copies a file from inside the jar to the specified location outside the
     * jar, retaining the file name. The default copy action is to not overwrite
     * an existing file.
     *
     * @param from the location of the internal resource.
     * @param to the location to copy the resource to.
     * @param overwrite if the copy task should overwrite existing files.
     */
    protected void copyResource(String from, Path to, boolean overwrite) {
        try {
            if (overwrite || !Files.exists(to)) {
                Files.createDirectories(to.getParent());
                Files.copy(this.getResourceAsStream(from), to, StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (Exception e) {
            AgriCore.getLogger("AgriCraft").error(
                    "Unable to copy Jar resource: \"{0}\" to: \"{1}\"!",
                    from,
                    to
            );
            e.printStackTrace();
        }
    }

    /**
     * Retrieves the requested resource by using the current thread's class
     * loader or the AgriCore class loader.
     *
     * @param location the location of the desired resource stream.
     * @return the resource, as a stream.
     */
    protected InputStream getResourceAsStream(String location) {
        InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(location);
        return in != null ? in : ResourceHelper.class.getResourceAsStream(location);
    }

    /**
     * Resolve a directory from a path and rename it.
     *
     * @param path the path of the directory to rename.
     * @param from the old directory name.
     * @param to the new directory name.
     * @param overwrite if the copy task should overwrite existing files.
     */
    public static void renameDirectory(Path path, String from, String to, boolean overwrite) {
        if (!Files.exists(path.resolve(from))) {
            return;
        }
        try {
            Files.walkFileTree(path.resolve(from), new SimpleFileVisitor<>(){

                @Override
                public FileVisitResult visitFile(Path from, BasicFileAttributes attrs) throws IOException {
                    String[] parts = from.toString().split(Pattern.quote(File.separator));
                    int index = parts.length-3;
                    parts[index] = to;
                    Path to = Paths.get(String.join(File.separator, parts));
                    AgriCore.getCoreLogger().info("moving {0} to {1}", from, to);
                    if (overwrite || !Files.exists(to)) {
                        Files.createDirectories(to.getParent());
                        Files.copy(from, to, StandardCopyOption.REPLACE_EXISTING);
                        Files.delete(from);
                    } else {
                        Path backupPath = Paths.get(from.toString().replaceAll("defaults", "backups/"));
                        Files.createDirectories(backupPath.getParent());
                        Files.copy(from, backupPath, StandardCopyOption.REPLACE_EXISTING);
                        Files.delete(from);
                    }
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                    try (Stream<Path> entries = Files.list(dir)) {
                        if (!entries.findFirst().isPresent()) {
                            Files.delete(dir);
                            return FileVisitResult.CONTINUE;
                        }
                    }
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
