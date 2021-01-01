package com.agricraft.agricore.config;

/**
 * Interface for AgriConfigurable instances that need name resolution based on
 * the instance.
 */
public interface AgriConfigurableInstance {

    String resolve(String input);

}
