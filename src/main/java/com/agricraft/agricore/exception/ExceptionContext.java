/*
 * 
 */
package com.agricraft.agricore.exception;

import java.util.Map;
import java.util.stream.Stream;

/**
 *
 * @author Ryan
 */
public interface ExceptionContext {
    
    ExceptionContext withEntry(String key, Object value);
    
    Stream<Map.Entry<String, Object>> getEntries();
    
    String toFormattedString();
    
}
