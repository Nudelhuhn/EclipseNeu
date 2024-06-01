package uebung3;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.ByteBuffer;

import javax.imageio.ImageIO;

import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

public class OGLIntro
{
	private static int vao_;

	private static int model_, view_, projection_;
	private static int albedo_;

	public static void main(String[] args)
	{
		// Initialize glfw 
		if (!GLFW.glfwInit())
		{
			System.err.println("Couldn't initialize glfw");
			System.exit(-1);
		}

		// Set the default window hints
		GLFW.glfwDefaultWindowHints();
		// And explicitely set the resizable and major + minor context version window hints
		GLFW.glfwWindowHint(GLFW.GLFW_RESIZABLE, GLFW.GLFW_FALSE);
		GLFW.glfwWindowHint(GLFW.GLFW_CONTEXT_VERSION_MAJOR, 3);
		GLFW.glfwWindowHint(GLFW.GLFW_CONTEXT_VERSION_MINOR, 3);
		// Get a window from the os via glfw and store its handle
		long window = GLFW.glfwCreateWindow(800, 600, "OpenGL Intro", 0, 0);

		// Make the windows OpenGL rendering context the currently active one
		GLFW.glfwMakeContextCurrent(window);
		// Create OpenGL capabilities based on the windows context version, which
		//  enables or disables the use of some OpenGL functions
		// Calling OpenGL functions that aren't usable in the current context
		//  throws a runtime exception
		GL.createCapabilities();

		Controller c = new Controller(80, 60);

		initShaderProgram();

		loadTexture("cat.jpg", GL13.GL_TEXTURE0);
		GL20.glUniform1i(albedo_, 0);

		// Create the buffers that store the vertex data
		initBuffers();

		// Make the window visible
		GLFW.glfwShowWindow(window);

		// Set the color buffer clear color, as a RGBA value
		GL11.glClearColor(0.3F, 0.3F, 0.3F, 1.0F);

		// Render loop, check if the window should be closed, otherwise
		//  render our geometry
		while (!GLFW.glfwWindowShouldClose(window))
		{
			// Update OpenGL status flags and features
			c.updateOpenGLBindings();

			// Clear the color buffer AAAAAAAAAAAAAAAAAAAAAAAAAAAANNNNNNNNNNNNNNNDDDDDDDDDDDDDDD the depth buffer bit
			// This is necessary, because otherwise the depth buffer is not cleared which results in either nothing showing
			//  up at all, or other rendering arifacts
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);

			GL20.glUniformMatrix4fv(model_, false, c.getModel());
			GL20.glUniformMatrix4fv(view_, false, c.getView());
			GL20.glUniformMatrix4fv(projection_, false, c.getProjection());

			// Draw the currently bound vbo's data
			GL30.glBindVertexArray(vao_);
			GL15.glDrawElements(GL11.GL_TRIANGLES, 6, GL11.GL_UNSIGNED_INT, 0);
			GL30.glBindVertexArray(0);

			// Swap our front and back buffer
			// The technique is called double buffering and works by alternatingly
			//  writing and reading from two distinct color buffers to mitigate
			//  rendering artifacts like screen tearing
			GLFW.glfwSwapBuffers(window);
			// Poll events from the operating system, like mouse and keyboard clicks,
			//  resizes, etc.
			// This also acts as a feedback for the operating system telling it, that
			//  the current application isn't hanging
			GLFW.glfwPollEvents();
		}
	}

	static void initShaderProgram()
	{
		// Create a new shader program
		int program = GL20.glCreateProgram();

		// Create the vertex and fragment shader and get their respective handles
		int vertex_shader = createShader(GL20.GL_VERTEX_SHADER, Shaders.VERTEX_SHADER);
		int fragment_shader = createShader(GL20.GL_FRAGMENT_SHADER, Shaders.FRAGMENT_SHADER);

		// Attach the two already created shaders to the program
		GL20.glAttachShader(program, vertex_shader);
		GL20.glAttachShader(program, fragment_shader);
		// And link them together
		GL20.glLinkProgram(program);

		// Validate the program to check for errors that happened while linking
		GL20.glValidateProgram(program);
		// And print the program linking info log
		System.out.println("Program link status: " + GL20.glGetProgramInfoLog(program));

		// Tell OpenGL to use our newly created program for all upcoming rendering operations
		GL20.glUseProgram(program);

		// Get the uniform locations from the current program and store them for later use
		model_ = GL20.glGetUniformLocation(program, "model");
		view_ = GL20.glGetUniformLocation(program, "view");
		projection_ = GL20.glGetUniformLocation(program, "projection");
		albedo_ = GL20.glGetUniformLocation(program, "albedo");
	}

	static void loadTexture(String path, int texture_id)
	{
		try
		{
			// Load an image reltive to the classpath of the current executable
			// If no image was found, an IOException is thrown
			BufferedImage img = ImageIO.read(OGLIntro.class.getClassLoader().getResourceAsStream(path));
			// And get the width and height for easier access
			int w = img.getWidth();
			int h = img.getHeight();

			// Create a byte buffer in which we will store the reordered color data
			// Reordering is neccessary, since OpenGL expects color values in RGBA format,
			//  but the Java buffered image contains them in ARGB format
			ByteBuffer buffer = BufferUtils.createByteBuffer(w * h * 4);
			// We iterate over the lines of the image
			for (int y = 0; y < h; ++y)
			{
				// And the columns
				for (int x = 0; x < w; ++x)
				{
					// And get the texel at the current position
					// Attention: We have flip the y coordinate since the image and uv coordinate systems are
					//  flipped along the y axis, which would flip the final texture when rendering as well
					int texel = img.getRGB(x, h - 1 - y);
					// Do some bit shifting to get the color channels in the right order
					buffer.put((byte) (texel >> 16));
					buffer.put((byte) (texel >> 8));
					buffer.put((byte) (texel >> 0));
					buffer.put((byte) (texel >> 24));
				}
			}
			// Reset the buffer write/read position to the start
			buffer.flip();

			// Generate an OpenGL texture and store its handle
			int texture = GL11.glGenTextures();
			// Mark the supplied texture id as the currently active texture
			// This way all the upcoming texture operations are stored in relation to this texture
			GL13.glActiveTexture(texture_id);
			// Bind the texture handle to the GL_TEXTURE_2D target
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture);

			// Set some texture parameters, which specify how to handle "abnormal" or ambiguous sampling situations
			GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
			GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
			GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL12.GL_CLAMP_TO_EDGE);
			GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL12.GL_CLAMP_TO_EDGE);

			// Push the texture data into VRAM
			GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA, w, h, 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE,
					buffer);
			// And generate the mipmaps for the texture
			GL30.glGenerateMipmap(GL11.GL_TEXTURE_2D);

		} catch (IOException e)
		{
			e.printStackTrace();
			System.exit(-1);
		}
	}

	static int createShader(int shader_type, String shader_source)
	{
		// Create a new shader on the gpu and get its handle
		int shader = GL20.glCreateShader(shader_type);
		// Use the supplied source code for the shader
		GL20.glShaderSource(shader, shader_source);
		// And compile it
		GL20.glCompileShader(shader);

		// Check if the compilation was successful or print the error log if something went wrong
		if (GL20.glGetShaderi(shader, GL20.GL_COMPILE_STATUS) == GL20.GL_FALSE)
		{
			String log = GL20.glGetShaderInfoLog(shader);
			String shader_prefix = shader_type == GL20.GL_VERTEX_SHADER ? "Vertex Shader: " : "Fragment Shader: ";
			System.err.println(shader_prefix + log);
		}

		// Return the handle of the newly created shader
		return shader;
	}

	static void initBuffers()
	{
		// Create our vertex data, composed of positions (x, y, z and w)
		//  as well as colors (r, g, b and alpha) and uv coords (u, v)
		//  in an interleaved buffer layout
		float[] points = new float[] {
			-0.5F, -0.5F, 0.0F, 1.0F,
			1.0F, 0.0F, 0.0F, 1.0F,
			0.0F, 0.0F,

			0.5F, -0.5F, 0.0F, 1.0F,
			0.0F, 1.0F, 0.0F, 1.0F,
			1.0F, 0.0F,

			0.5F, 0.5F, 0.0F, 1.0F,
			0.0F, 0.0F, 1.0F, 1.0F,
			1.0F, 1.0F,

			-0.5F, 0.5F, 0.0F, 1.0F,
			1.0F, 1.0F, 1.0F, 1.0F,
			0.0F, 1.0F,
		};

		// The indices describe which three vertices form a triangle
		int[] indices = new int[] {
			0, 1, 2,
			3, 0, 2
		};

		// Generate the vertex array object that will acts as the storage for
		//  the buffer layout meta information
		vao_ = GL30.glGenVertexArrays();
		// Bind the vao, which makes it the currently active one
		GL30.glBindVertexArray(vao_);

		// Create our Vertex Buffer Object (VBO) that will hold our vertex data in
		//  graphics memory
		int vbo = GL15.glGenBuffers();
		// Bind the buffer to the GL_ARRAY_BUFFER buffer binding point, which specifically hold vertex data
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vbo);
		// Fill the buffer currently bound to the GL_ARRAY_BUFFER buffer binding point with some data
		// The usage hint GL_STATIC_DRAW tells OpenGL that the data will probably will be only modified once
		//  (aka. when this function call writes the data) and used repeatedly as the source of rendering operations
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, points, GL15.GL_STATIC_DRAW);

		// Specifies the data layout in the currently bound buffer for the vertex attribute at index 0
		// The parameters have the following meanings:
		// - index:			The vertex pointers index is a running starting from 0 or a formerly acquired layout location (glGetAttribLocation)
		// - size:			The number of elements for the current vertex attribute that are grouped together (here x, y, z and w coordinates, making it 4)
		// - type:			The type the supplied data has, constants are found in GL11 namespace
		// - normalized:	Wether integer values are to be mapped to -1 to 1 (for signed) or 0 to 1 (for unsigned) when being converted to float values
		// - stride:		Byte offset between vertex attributes of the same kind inside the buffer
		// - offset:		Where the first vertex attribute of this kind starts in the buffer
		GL20.glVertexAttribPointer(0, 4, GL11.GL_FLOAT, false, 10 * 4, 0);
		// Enable the vertex attrib array so that it can be used when rendering vertex data from the buffer
		GL20.glEnableVertexAttribArray(0);

		// Specify a second vertex attribute pointer, which specifies the layout of the color data in the buffer
		GL20.glVertexAttribPointer(1, 4, GL11.GL_FLOAT, false, 10 * 4, 4 * 4);
		// And enable it as well
		GL20.glEnableVertexAttribArray(1);

		// Specify a third vertex attribute pointer, which specifies the layout of the uv data in the buffer
		GL20.glVertexAttribPointer(2, 2, GL11.GL_FLOAT, false, 10 * 4, 8 * 4);
		// And enable it as well
		GL20.glEnableVertexAttribArray(2);

		// Generate another buffer that will hold our index data
		int ibo = GL15.glGenBuffers();
		// Bind it to the element array buffer target, which explicitely holds index data
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, ibo);
		// And push the index data into this buffer
		GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, indices, GL15.GL_STATIC_DRAW);

		// Unbind the vao, for good measure
		GL30.glBindVertexArray(0);
	}
}
