package Utils.Serializer.MovableGameObject;

import Game.Objects.Player;
import com.google.gson.*;

import java.lang.reflect.Type;

public class PlayerSerializer implements JsonSerializer<Player> {

    @Override
    public JsonElement serialize(Player player, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject playerJson = new JsonObject();

        playerJson.add("coord", jsonSerializationContext.serialize(player.getPosition()));
        playerJson.addProperty("playerNumber", player.getPlayerNumber());
        playerJson.addProperty("wins", player.wins);
        playerJson.add("powerUpProps", jsonSerializationContext.serialize(player.powerUpList.getPowerUpProps()));

        JsonArray powerUpList = new JsonArray();
        player.powerUpList.getPowerUps().forEach(p -> {
           JsonElement powerUpElement = jsonSerializationContext.serialize(p);
           JsonObject powerUpJson = powerUpElement.getAsJsonObject();
           powerUpJson.addProperty("name", p.getClass().getSimpleName());
           powerUpList.add(powerUpJson);
        });

        playerJson.add("powerUpList", powerUpList);

        return playerJson;
    }
}
