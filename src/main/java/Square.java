import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL33;
import org.lwjgl.system.MemoryUtil;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public class Square {
    private final int[] indices = {
            0, 1, 3,
            1, 2, 3
    };
    private final float[] verticies;

    private int vaoId;
    private int vboId;
    private int eboId;
    private boolean isWall;
    private float sideSize;

    public Square(int vaoId, int vboId, int eboId, boolean isWall, float sideSize, int x, int y) {
        this.vaoId = vaoId;
        this.vboId = vboId;
        this.eboId = eboId;
        this.isWall = isWall;
        this.verticies = new float[]{
                -1f + (sideSize * (x + 1)), 1f - (sideSize * y), 0.0f, // 0 -> Top right
                -1f + (sideSize * (x + 1)), 1f - (sideSize * (y + 1)), 0.0f, // 1 -> Bottom right
                -1f + (sideSize * x), 1f - (sideSize * (y + 1)), 0.0f, // 2 -> Bottom left
                -1f + (sideSize * x), 1f - (sideSize * y), 0.0f, // 3 -> Top left
        };
    }

    public float getSideSize() {
        return sideSize;
    }

    public void setSideSize(float sideSize) {
        this.sideSize = sideSize;
    }

    public boolean isWall() {
        return isWall;
    }

    public void setWall(boolean wall) {
        isWall = wall;
    }

    public int getVaoId() {
        return vaoId;
    }

    public void setVaoId(int vaoId) {
        this.vaoId = vaoId;
    }

    public int getVboId() {
        return vboId;
    }

    public void setVboId(int vboId) {
        this.vboId = vboId;
    }

    public int getEboId() {
        return eboId;
    }

    public void setEboId(int eboId) {
        this.eboId = eboId;
    }

    public void render(long window) {
        GL33.glBindVertexArray(this.vaoId);
        GL33.glDrawElements(GL33.GL_TRIANGLES, indices.length, GL33.GL_UNSIGNED_INT, 0);
    }

    public void init(long window) {
        //Shaders.initShaders();
        GL33.glBindVertexArray(this.vaoId);
        GL33.glBindBuffer(GL33.GL_ELEMENT_ARRAY_BUFFER, this.eboId);
        IntBuffer ib = BufferUtils.createIntBuffer(this.indices.length)
                .put(this.indices)
                .flip();
        GL33.glBufferData(GL33.GL_ELEMENT_ARRAY_BUFFER, ib, GL33.GL_STATIC_DRAW);

        GL33.glBindBuffer(GL33.GL_ARRAY_BUFFER, this.vboId);

        FloatBuffer fb = BufferUtils.createFloatBuffer(this.verticies.length)
                .put(verticies)
                .flip();

        GL33.glBufferData(GL33.GL_ARRAY_BUFFER, fb, GL33.GL_STATIC_DRAW);

        GL33.glVertexAttribPointer(0, 3, GL33.GL_FLOAT, false, 0, 0);
        GL33.glEnableVertexAttribArray(0);

        MemoryUtil.memFree(fb);
    }


}
