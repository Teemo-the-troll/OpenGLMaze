import org.lwjgl.opengl.GL33;

import java.util.Random;

public class Maze {

    private final Square[][] body;
    private int height;
    private int width;


    public Maze(int width, int height) {
        Random random = new Random();
        Square[][] body = new Square[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                body[i][j] = new Square(GL33.glGenVertexArrays(), GL33.glGenBuffers(), GL33.glGenBuffers(), random.nextBoolean(), 2f / width, i, j);
            }
        }
        this.body = body;
        this.height = height;
        this.width = width;
    }

    public Maze(String source) {
        String[] rawRows = source.split("\n");
        this.height = rawRows.length;
        this.width = rawRows[0].length();
        Square[][] body = new Square[this.height][this.width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                body[i][j] = new Square(GL33.glGenVertexArrays(), GL33.glGenBuffers(), GL33.glGenBuffers(), Integer.parseInt(String.valueOf(rawRows[i].charAt(j))) == 1, 2f / this.width, i, j);
            }
        }
        this.body = body;

    }

    public Square[][] getBody() {
        return body;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }


}
