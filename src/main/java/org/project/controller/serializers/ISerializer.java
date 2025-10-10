package org.project.controller.serializers;

import org.json.JSONObject;

public interface ISerializer<T> {
    JSONObject serialize(T model);
    T deserialize(JSONObject json);
}
