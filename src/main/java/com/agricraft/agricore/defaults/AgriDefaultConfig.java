package com.agricraft.agricore.defaults;

import com.agricraft.agricore.config.AgriConfigAdapter;

public class AgriDefaultConfig implements AgriConfigAdapter {
    @Override
    public boolean enableJsonWriteback() {
        return true;
    }

    @Override
    public boolean enableLogging() {
        return true;
    }
}
