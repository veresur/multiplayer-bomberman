package Utils.Serializer;

import Game.Game;
import Game.Map;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

public class GameSerializer implements JsonSerializer<Game> {

    @Override
    public JsonElement serialize(Game game, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject gameJson = new JsonObject();

        gameJson.add("map", jsonSerializationContext.serialize(game.getMap(), Map.class));

        return gameJson;
    }
}
