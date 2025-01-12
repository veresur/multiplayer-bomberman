package Utils.Serializer.Utils;

import Utils.Coordinate;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

public class CoordinateSerializer implements JsonSerializer<Coordinate> {
    @Override
    public JsonElement serialize(Coordinate coordinate, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject coordJson = new JsonObject();

        coordJson.addProperty("X", coordinate.X);
        coordJson.addProperty("Y", coordinate.Y);

        return coordJson;
    }
}
