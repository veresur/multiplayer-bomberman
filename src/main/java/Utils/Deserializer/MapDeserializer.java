package Utils.Deserializer;

public class MapDeserializer {
    public EnemyDeserializer[] enemies;
    public PlayerDeserializer[] players;
    public GameObjectDeserializer[] objects;
    public int mapOption;
    public int currentRound;
    public int roundCount;
    public int playerCount;

    public void setPlayerCount(int playerCount) {
        this.playerCount = playerCount;
    }
    public void setEnemies(EnemyDeserializer[] enemies) {
        this.enemies = enemies;
    }

    public void setPlayers(PlayerDeserializer[] players) {
        this.players = players;
    }

    public void setObjects(GameObjectDeserializer[] objects) {
        this.objects = objects;
    }

    public void setMapOption(int mapOption) {
        this.mapOption = mapOption;
    }

    public void setCurrentRound(int currentRound) {
        this.currentRound = currentRound;
    }

    public void setRoundCount(int roundCount) {
        this.roundCount = roundCount;
    }
}
