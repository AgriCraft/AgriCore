package com.agricraft.agricore.util;

import com.agricraft.agricore.core.AgriCore;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Collection;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.reflections.Reflections;
import org.reflections.scanners.ResourcesScanner;
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
    public static void copyResources(Stream<Path> pathStream, Predicate<String> nameFilter, Predicate<String> dirFilter, Function<String, Path> toFunction, boolean overwrite) {
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
        this.reflections = new Reflections(
                new ConfigurationBuilder()
                        .addScanners(new ResourcesScanner())
                        .addUrls(urls));
    }

    /**
     * Finds all resources in the class path matching the given predicate
     * @param nameFilter file name predicate
     * @return set of all filenames matching the name filter
     */
    protected Set<String> findResources(Predicate<String> nameFilter) {
        return this.reflections.getResources(nameFilter::test);
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

}
