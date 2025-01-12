package Utils.Serializer.MovableGameObject;

import Game.Objects.Enemy;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

public class EnemySerializer implements JsonSerializer<Enemy> {
    @Override
    public JsonElement serialize(Enemy enemy, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject enemyJson = new JsonObject();
        enemyJson.add("coord", jsonSerializationContext.serialize(enemy.getPosition()));
        enemyJson.add("direction", jsonSerializationContext.serialize(enemy.getDirection()));

        return enemyJson;
    }
}
