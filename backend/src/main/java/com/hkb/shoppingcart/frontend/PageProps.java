package com.hkb.shoppingcart.frontend;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class PageProps {
    @NonNull String baseUrl;
}
