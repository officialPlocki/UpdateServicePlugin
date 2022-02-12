package me.refluxo.updateservice.common.files;

import java.util.Map;

public interface ConfigurationSerializable {

    Map<String, Object> serialize();
}