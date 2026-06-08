package org.project.serializers.interfaces;

import org.json.JSONObject;

public interface Serializable<T> {
    JSONObject serialize(T model);
    T deserialize(JSONObject json);
}
