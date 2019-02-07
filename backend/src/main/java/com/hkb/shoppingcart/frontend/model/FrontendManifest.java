package com.hkb.shoppingcart.frontend.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
public class FrontendManifest {
    @JsonProperty("main.js")
    String mainScriptName;
}
