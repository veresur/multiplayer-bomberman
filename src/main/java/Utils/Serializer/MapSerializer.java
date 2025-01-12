package Utils.Serializer;

import Game.Map;
import Game.Objects.*;
import Utils.Coordinate;
import com.google.gson.*;

import java.lang.reflect.Type;

public class MapSerializer implements JsonSerializer<Map> {

    @Override
    public JsonElement serialize(Map map, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject mapObject = new JsonObject();

        JsonArray enemyArray = new JsonArray();
        map.enemies.forEach(enemy -> enemyArray.add(jsonSerializationContext.serialize(enemy, Enemy.class)));
        mapObject.add("enemies", enemyArray);

        JsonArray playerArray = new JsonArray();
        map.players.forEach((playerKey, player) -> {
            JsonElement playerJson = jsonSerializationContext.serialize(player, Player.class);

            JsonObject playerJsonObject = playerJson.getAsJsonObject();
            playerJsonObject.addProperty("key", playerKey.toString());

            playerArray.add(playerJsonObject);
        });
        mapObject.add("players", playerArray);

        JsonArray objectArray = new JsonArray();
        for(int i = 0; i < map.map.length; i++) {
            for (int j = 0; j < map.map[i].length; j++) {
                GameObject currentObject = map.map[i][j];
                if (currentObject == null) {continue;}

                JsonElement objectElement = jsonSerializationContext.serialize(map.map[i][j]);

                JsonObject objectJsonObject = objectElement.getAsJsonObject();
                objectJsonObject.add("coord", jsonSerializationContext.serialize(new Coordinate(i, j)));
                objectJsonObject.addProperty("name", currentObject.getClass().getSimpleName());

                if (currentObject instanceof Barricade) {
                    objectJsonObject.addProperty("owner", ((Barricade) currentObject).getOwner().getPlayerNumber());
                }
                else if (currentObject instanceof Bomb) {
                    objectJsonObject.addProperty("owner", ((Bomb) currentObject).GetOwner().getPlayerNumber());
                }
                else if (currentObject instanceof PickUp) {
                    objectJsonObject.addProperty("type", ((PickUp) currentObject).getPowerUp().getClass().getSimpleName());
                }

                objectArray.add(objectJsonObject);
            }
        }
        mapObject.add("objects", objectArray);

        mapObject.addProperty("roundCount", map.roundCount);
        mapObject.addProperty("currentRound", map.currentRound);
        mapObject.addProperty("mapOption", map.getMapOption());
        mapObject.addProperty("playerCount", map.getPlayerCount());

        return mapObject;
    }
}
