package com.agricraft.agricore.util;

import com.agricraft.agricore.core.AgriCore;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import org.reflections.Reflections;
import org.reflections.scanners.ResourcesScanner;

public class ResourceHelper {
    /**
     * Copies all resources in the class path matching the given name predicate, as well as the file structure predicate to a new directory
     *
     * @param nameFilter the predicate for the file name
     * @param dirFilter the predicate for the file (directory) structure
     * @param toFunction function specifying the destination where each file should be copied to
     * @param overwrite specifies if files which already exist should be overwritten or not
     */
    public static void copyResources(Predicate<String> nameFilter, Predicate<String> dirFilter, Function<String, Path> toFunction, boolean overwrite) {
        ResourceHelper helper = new ResourceHelper();
        helper.findResources(nameFilter).stream()
                .filter(dirFilter)
                .forEach(r -> helper.copyResource(r, toFunction.apply(r), overwrite)
                );
    }

    /**
     * Reflections instance used to find jsons
     */
    private final Reflections reflections;

    /**
     * The Reflections object can use quite some memory, therefore we instantiate a new one when we need it,
     * and discard it afterwards
     */
    protected ResourceHelper() {
        this.reflections = new Reflections(null, new ResourcesScanner());
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
