package uebung3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.stream.Collectors;

import org.lwjgl.BufferUtils;

import matrixPackage.Matrix;

/**
 * Assortment of static methods for dealing with some OpenGL camera shenanigans.
 * Forwarding is not allowed without permission!
 *
 * @author Nick Baldig
 *
 */
public class LWJGLHelper
{

	/**
	 * Creates an orthographic projection matrix.
	 * 
	 * @param l
	 *            How many units our viewing frustum extends to the left
	 * @param r
	 *            How many units our viewing frustum extends to the right
	 * @param b
	 *            How many units our viewing frustum extends to the bottom
	 * @param t
	 *            How many units our viewing frustum extends to the top
	 * @param n
	 *            How near the near clipping pane is to our camera (very small
	 *            values are advised)
	 * @param f
	 *            How far away the far clipping pane is to our camera (large values
	 *            are advised)
	 * @return a float array representing the projection matrix
	 */
	public static float[] Orthographic(float l, float r, float b, float t, float n, float f)
	{

		float[] ortho = new float[16];

		ortho[0] = 2.0F / (r - l);
		ortho[5] = 2.0F / (t - b);
		ortho[10] = -2.0F / (f - n);

		ortho[12] = -(r + l) / (r - l);
		ortho[13] = -(t + b) / (t - b);
		ortho[14] = -(f + n) / (f - n);
		ortho[15] = 1;

		return ortho;
	}

	/**
	 * Creates a perspective projection matrix.
	 * 
	 * @param w
	 *            the width of our window
	 * @param h
	 *            the height of our window
	 * @param n
	 *            How near the near clipping pane is to our camera (very small
	 *            values are advised)
	 * @param f
	 *            How far away the far clipping pane is to our camera (large values
	 *            are advised)
	 * @param fov
	 *            The field of view (FOV) of our camera
	 * @return a float array representing our perspective projection matrix
	 */
	public static float[] Perspective(float w, float h, float n, float f, float fov)
	{

		float[] persp = new float[16];

		float tanOfHalfFov = (float) Math.tan(Math.toRadians(fov / 2.0));
		float aspectRatio = w / h;

		persp[0] = 1 / (aspectRatio * tanOfHalfFov);
		persp[5] = 1 / tanOfHalfFov;

		persp[10] = (f + n) / (n - f);
		persp[11] = (2.0F * f * n) / (n - f);

		persp[14] = -1.0F;

		return persp;
	}

	/**
	 * Creates the look at matrix used for our view matrix
	 * 
	 * @param camera
	 *            the position of the camera (a 3 dimensional float array {x, y, z})
	 * @param up
	 *            the vector that points up in our world (usually {0, 1, 0})
	 * @param point
	 *            the position the camera look at (a 3 dimensional float array {x,
	 *            y, z})
	 * @return
	 */
	public static float[] LookAtMat(float[] camera, float[] up, float[] point)
	{

		up = Normalize(up);

		float[] z = new float[] {
			point[0] - camera[0], point[1] - camera[1], point[2] - camera[2]
		};
		z = Normalize(z);
		float[] x = Cross(z, up);
		float[] y = Cross(x, z);

		double[][] rot = new double[][] {
			{
				x[0], x[1], x[2], 0
			},
			{
				y[0], y[1], y[2], 0
			},
			{
				-z[0], -z[1], -z[2], 0
			},
			{
				0, 0, 0, 1
			}
		};
		double[][] translation = new double[][] {
			{
				1, 0, 0, -camera[0]
			},
			{
				0, 1, 0, -camera[1]
			},
			{
				0, 0, 1, -camera[2]
			},
			{
				0, 0, 0, 1
			}
		};

		double[][] resMat = Matrix.matMult(rot, translation);

		float[] res = new float[16];

		for (int i = 0; i < 4; ++i)
		{
			for (int j = 0; j < 4; ++j)
			{
				res[i * 4 + j] = (float) resMat[j][i];
			}
		}

		return res;
	}

	/**
	 * Normalize the given vector
	 * 
	 * @param vec
	 *            the vector that should be normalized
	 * @return the normalized vector
	 */
	private static float[] Normalize(float[] vec)
	{
		float length = (float) Math.sqrt(vec[0] * vec[0] + vec[1] * vec[1] + vec[2] * vec[2]);
		return new float[] {
			vec[0] /= length, vec[1] /= length, vec[2] /= length
		};
	}

	/**
	 * Cross product of two 3 dimensional vectors
	 * 
	 * @param v1
	 *            the first vector
	 * @param v2
	 *            the second vector
	 * @return the result of the cross product
	 */
	private static float[] Cross(float[] v1, float[] v2)
	{
		return new float[] {
			v1[1] * v2[2] - v1[2] * v2[1], v1[2] * v2[0] - v1[0] * v2[2], v1[0] * v2[1] - v1[1] * v2[0]
		};
	}

	/**
	 * Convert an array into a float buffer
	 * 
	 * @param values
	 *            a float array
	 * @return the float buffer with all the values of the array
	 */
	public static FloatBuffer Convert(float[] values)
	{

		FloatBuffer buf = BufferUtils.createFloatBuffer(values.length);
		buf.put(values);
		buf.flip();

		return buf;

	}

	/**
	 * Convert an array into an int buffer
	 * 
	 * @param values
	 *            an int array
	 * @return the int buffer with all the values of the array
	 */
	public static IntBuffer Convert(int[] values)
	{

		IntBuffer buf = BufferUtils.createIntBuffer(values.length);
		buf.put(values);
		buf.flip();

		return buf;

	}

	/**
	 * Converts a two dimensional float array into one dimensional float
	 * array and automatically transposes it
	 * 
	 * @param matrix
	 *            the two dimensional float array
	 * @return the one dimensional array representing the transposed matrix
	 */
	public static float[] ConvertMat(float[][] matrix)
	{

		float[] res = new float[16];
		for (int i = 0; i < 4; ++i)
		{
			for (int j = 0; j < 4; ++j)
			{
				res[i * 4 + j] = matrix[j][i];
			}
		}
		return res;
	}

	/**
	 * Converts a two dimensional double array into one dimensional float
	 * array and automatically transposes it
	 * 
	 * @param matrix
	 *            the two dimensional double array
	 * @return the one dimensional array representing the transposed matrix
	 */
	public static float[] ConvertMat(double[][] matrix)
	{

		float[] res = new float[16];
		for (int i = 0; i < 4; ++i)
		{
			for (int j = 0; j < 4; ++j)
			{
				res[i * 4 + j] = (float) matrix[j][i];
			}
		}
		return res;
	}

	/**
	 * Loads the content of the file at location into a string and returns it
	 * 
	 * @param location
	 *            The location of the file to load the content from
	 * @return A string containing the content of the file
	 */
	public static String loadStringFromFile(String location)
	{
		InputStreamReader stream = new InputStreamReader(
				LWJGLHelper.class.getClassLoader().getResourceAsStream(location));
		return new BufferedReader(stream).lines().collect(Collectors.joining("\n"));
	}
}