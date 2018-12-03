package com.glhf.bomberball.maze;

import com.glhf.bomberball.gameobject.GameObject;
import com.google.gson.*;

import java.lang.reflect.Type;

public class MazeTypeAdapter implements JsonSerializer<Object>, JsonDeserializer<Object> {
    private static Gson gson = new Gson();
    private static final String CLASS_META_KEY = "_class";

    @Override
    public Object deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject jsonObj = (JsonObject) jsonElement;
        String className = jsonObj.get(CLASS_META_KEY).getAsString();
        try {
            Class<?> clz = Class.forName(className);
            Object o = gson.fromJson(jsonElement, clz);
            if (o instanceof GameObject) {
                ((GameObject) o).initialize();
            }
            return o;
        } catch (ClassNotFoundException e) {
            throw new JsonParseException(e);
        }
    }

    @Override
    public JsonElement serialize(Object object, Type type, JsonSerializationContext jsonSerializationContext) {
        //System.out.println(object);
        JsonElement jsonEle = gson.toJsonTree(object);
        jsonEle.getAsJsonObject().addProperty(CLASS_META_KEY, object.getClass().getCanonicalName());
        return jsonEle;
    }
}
