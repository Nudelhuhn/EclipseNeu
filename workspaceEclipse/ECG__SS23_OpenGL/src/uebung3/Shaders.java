package uebung3;

public class Shaders
{
	public static final String VERTEX_SHADER = """
			#version 330 //OpenGL Version 3.3 is used

			// We take a position and a color per vertex as incoming vertex attributes
			in vec4 in_position;
			in vec4 in_color;
			in vec2 in_uv; 				//uv = Texture coordinates, gives 3D-points a position in the 2D-texture

			// And we push a color varying variable out of the vertex shader into the fragment shader
			out vec4 var_color;
			out vec2 var_uv;

			// variables declared in the shader, which are passed to the shader by the application 
			// and remain constant during the rendering process
			uniform mat4 model;			// pass 4x4-model-transformation-matrix to shader
			uniform mat4 view;			// 4x4-view-transformation-matrix that determines position & orientation of the virtual camera
			uniform mat4 projection;	// enables transfer of 4x4-projection-m to shader to project scene into 2D-screen-space
			uniform int renderAxes;		// decides whether coordinate axes or cube should be rendered

			// Apply the transformations to the position. Passing on color and texture coordinates
			void main()
			{
				if (renderAxes == 0) {	// if 0 render cube, else render coordinate axes
					// Assign the special gl_Position variable to the incoming position vertex attribute
			    	gl_Position = projection * view * model * in_position;
			    } else {
			    	gl_Position = projection * view * in_position;
			    }
				// And pass the color through
				var_color = in_color;
				var_uv = in_uv;
			}
			""";

	public static final String FRAGMENT_SHADER = """
			#version 330

			// Accept a varying variable var_color
			// incoming varying variables from vertex shader (out => in)
			in vec4 var_color;
			in vec2 var_uv;

			// In the fragment shader, calculations are performed for each fragment (pixel) created during
			// the rasterization process to determine the color of the fragment. The variable "out_color" 
			// is used to store this calculated color of the fragment and finally push the final color out
			// of the shader
			out vec4 out_color;

			// passing a texture as input to fragment shader
			uniform sampler2D albedo;
			
			uniform int renderAxes;
			// passing the blend between the texture color and the calculated colors to fragment shader
			uniform float color_interpolation;

			void main()
			{
				if (renderAxes == 1) {
			        out_color = var_color;
			    } else {
			    	vec4 texture_color = texture(albedo, var_uv);
			        vec4 interpolated_color = mix(texture_color, var_color, 1.0F - color_interpolation);
			        out_color = interpolated_color;
			    }
			}
			""";
}
