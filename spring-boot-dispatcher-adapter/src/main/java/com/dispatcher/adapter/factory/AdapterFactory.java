package com.dispatcher.adapter.factory;

import com.dispatcher.adapter.common.WarehouseAdapter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class AdapterFactory {

    private final Map<String, WarehouseAdapter> adapterMap;

    public AdapterFactory(List<WarehouseAdapter> adapters) {
        adapterMap = adapters.stream()
                .collect(Collectors.toMap(
                        WarehouseAdapter::getPlatform,
                        Function.identity()
                ));
    }

    public WarehouseAdapter getAdapter(String platform) {

        WarehouseAdapter adapter = adapterMap.get(platform);

        if (adapter == null) {
            throw new RuntimeException("Unsupported platform: " + platform);
        }

        return adapter;
    }
}
