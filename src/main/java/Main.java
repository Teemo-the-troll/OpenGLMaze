import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL33;

public class Main {
    public static void main(String[] args) throws Exception {
        String source = "101111111000111011101010000001\n" +
                "100000101101101100111011111111\n" +
                "110011100111000110100010000100\n" +
                "010110000000010010101110110111\n" +
                "011100111101110110111010100001\n" +
                "100011100111001100100000111011\n" +
                "100000100010011000101111101010\n" +
                "110011101110010111111000000010\n" +
                "010110100001110000001010111010\n" +
                "111100101111001111011010101011\n" +
                "001000001000001010010110101001\n" +
                "111011101011101011110100101101\n" +
                "101010101010111000001110100111\n" +
                "100010111010000111001010100001\n" +
                "111110000011001101011010101101\n" +
                "100100111101011001110001101001\n" +
                "101100100101000011011011001001\n" +
                "011001100101111010010010001011\n" +
                "110111001110001110110110011010\n" +
                "100100111011100010101100110010\n" +
                "100100000000111010101011101011\n" +
                "101111011110001011001010001001\n" +
                "100001010011001001101011001101\n" +
                "110011011101101100101001111001\n" +
                "011010000100100110101011001001\n" +
                "001011001101100010001000011001\n" +
                "111001111001011010111011110011\n" +
                "010000100001010011100010001110\n" +
                "011110001111010000000111001000\n" +
                "110011111001011111111101111111";

        String source2 = "1110111101\n" +
                "0010010111\n" +
                "1011110010\n" +
                "1100000011\n" +
                "0101111001\n" +
                "0100001001\n" +
                "0111111001\n" +
                "1100100111\n" +
                "1000101101\n" +
                "1110111001";

        String source3 = "10111\n" +
                "10001\n" +
                "11101\n" +
                "01001\n" +
                "11111";

        long window = Window.windowCreate();
        Maze maze = new Maze(source3); // change this to change the render

        for (Square[] row : maze.getBody()) {
            for (Square square : row) {
                square.init(window);
            }
        }
        while (!GLFW.glfwWindowShouldClose(window)) {

            // Change the background color
            GL33.glClearColor(1f, 0f, 0f, 1f);
            GL33.glClear(GL33.GL_COLOR_BUFFER_BIT);

            for (Square[] row : maze.getBody()) {
                for (Square square : row) {
                    if (square.isWall())
                        square.render(window);
                }
            }

            // Swap the color buffer -> screen tearing solution
            GLFW.glfwSwapBuffers(window);
            // Listen to input
            GLFW.glfwPollEvents();
        }

        GLFW.glfwTerminate();
    }
}
