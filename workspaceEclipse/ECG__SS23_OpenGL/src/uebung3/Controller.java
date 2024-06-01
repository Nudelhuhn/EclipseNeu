package uebung3;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;

import org.lwjgl.opengl.GL11;

import matrixPackage.Matrix;

public class Controller extends JFrame
{
	private float window_width_, window_height_;
	private boolean use_perspective_projection_;

	private boolean enable_depth_test_ = false;
	private boolean enable_face_cull_ = false;
	private boolean cull_front_faces_ = false;

	private float[] model_, view_, projection_;
	private float[] light_;

	// Model matrix related attributes
	private float x_rot_, y_rot_, z_rot_;
	private float x_trans_, y_trans_, z_trans_;
	private float scale_ = 1.0F;

	// View matrix related attributes
	private float[] view_pos_, view_up_, view_point_;

	// Perspective projection matrix related attributes
	private float perspective_near_ = 1.0F, perspective_far_ = 50.0F, fov_ = 60;

	// Perspective projection matrix related attributes
	private float orthographic_near_ = 0.1F, orthographic_far_ = 50.0F;

	// Light position
	private float light_rotation_ = 0.0F, light_y_ = 5.0F, light_distance_ = 20.0F;

	private float color_texture_interpolation_ = 0.5F;

	public Controller(float window_width, float window_height)
	{
		window_width_ = window_width;
		window_height_ = window_height;
		use_perspective_projection_ = true;

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
		setResizable(false);

		view_pos_ = new float[] { 0.0F, 0.0F, 5.0F };
		view_up_ = new float[] { 0.0F, 1.0F, 0.0F };
		view_point_ = new float[] { 0.0F, 0.0F, 0.0F };

		updateModel();
		updateView();
		updateProjection();
		updateLight();

		// Create the sub panel that influences the model matrix

		JPanel model_panel = new JPanel();
		model_panel.setBorder(BorderFactory.createTitledBorder("Model"));
		model_panel.setLayout(new BoxLayout(model_panel, BoxLayout.Y_AXIS));

		model_panel.add(new ComplexSlider("X Rotation", -360, 360, 0, this::updateXRotation));
		model_panel.add(new ComplexSlider("Y Rotation", -360, 360, 0, this::updateYRotation));
		model_panel.add(new ComplexSlider("Z Rotation", -360, 360, 0, this::updateZRotation));

		model_panel.add(new ComplexSlider("X Translation", -100, 100, 0, this::updateXTranslation));
		model_panel.add(new ComplexSlider("Y Translation", -100, 100, 0, this::updateYTranslation));
		model_panel.add(new ComplexSlider("Z Translation", -100, 100, 0, this::updateZTranslation));

		model_panel.add(new ComplexSlider("Scale", 0.1F, 10.0F, 1.0F, 0.1F, this::updateScale));

		add(model_panel);

		// Create the sub panel that influences the view matrix

		JPanel view_panel = new JPanel();
		view_panel.setBorder(BorderFactory.createTitledBorder("View"));
		view_panel.setLayout(new BoxLayout(view_panel, BoxLayout.Y_AXIS));

		view_panel.add(new ComplexSlider("X Pos", -20, 20, view_pos_[0], 0.01F, this::updateXPos));
		view_panel.add(new ComplexSlider("Y Pos", -20, 20, view_pos_[1], 0.01F, this::updateYPos));
		view_panel.add(new ComplexSlider("Z Pos", -20, 20, view_pos_[2], 0.01F, this::updateZPos));

		view_panel.add(new ComplexSlider("X LookAt", -100, 100, 0, this::updateXLookAt));
		view_panel.add(new ComplexSlider("Y LookAt", -100, 100, 0, this::updateYLookAt));
		view_panel.add(new ComplexSlider("Z LookAt", -100, 100, 0, this::updateZLookAt));

		add(view_panel);

		JPanel projection_panel = new JPanel();
		projection_panel.setLayout(new BoxLayout(projection_panel, BoxLayout.Y_AXIS));

		JCheckBox perspective_toggle = new JCheckBox("Use Perspective Projection", use_perspective_projection_);
		projection_panel.add(perspective_toggle);
		// Create the sub panel that influences the perspective projection matrix

		JPanel perspective_projection_panel = new JPanel();
		perspective_projection_panel.setBorder(BorderFactory.createTitledBorder("Perspective Projection"));
		perspective_projection_panel.setLayout(new BoxLayout(perspective_projection_panel, BoxLayout.Y_AXIS));

		perspective_projection_panel
				.add(new ComplexSlider("Near Plane", 0.0F, 5.0F, perspective_near_, 0.1F, this::updatePerspectiveNear));
		perspective_projection_panel
				.add(new ComplexSlider("Far Plane", 1.0F, 100.0F, perspective_far_, 1.0F, this::updatePerspectiveFar));
		perspective_projection_panel.add(new ComplexSlider("FOV", 30.0F, 180.0F, fov_, 1.0F, this::updateFOV));

		projection_panel.add(perspective_projection_panel);

		// Create the sub panel that influences the orthographic projection matrix

		JPanel orthographic_projection_panel = new JPanel();
		orthographic_projection_panel.setBorder(BorderFactory.createTitledBorder("Orthographic Projection"));
		orthographic_projection_panel.setLayout(new BoxLayout(orthographic_projection_panel, BoxLayout.Y_AXIS));

		orthographic_projection_panel.add(
				new ComplexSlider("Near Plane", 0.0F, 1.0F, orthographic_near_, 0.01F, this::updateOrhtographicNear));
		orthographic_projection_panel.add(
				new ComplexSlider("Far Plane", 1.0F, 100.0F, orthographic_far_, 1.0F, this::updateOrhtographicFar));

		projection_panel.add(orthographic_projection_panel);

		// Register the perspective toggle action listener and setup the projection panel's childrens visibility

		perspective_toggle.addActionListener(e ->
		{
			use_perspective_projection_ = ((JCheckBox) e.getSource()).isSelected();
			updateProjection();

			perspective_projection_panel.setVisible(use_perspective_projection_);
			orthographic_projection_panel.setVisible(!use_perspective_projection_);
		});

		perspective_projection_panel.setVisible(use_perspective_projection_);
		orthographic_projection_panel.setVisible(!use_perspective_projection_);

		add(projection_panel);

		// Add the light controls
		JPanel light_panel = new JPanel();
		light_panel.setBorder(BorderFactory.createTitledBorder("Light"));
		light_panel.setLayout(new BoxLayout(light_panel, BoxLayout.Y_AXIS));

		light_panel
				.add(new ComplexSlider("Light Rotation", -360, 360, light_rotation_, 1.0F, this::updateLightRotation));
		light_panel.add(new ComplexSlider("Light Height", -20, 20, light_y_, 0.1F, this::updateLightY));

		add(light_panel);

		// Add the opengl specific controls
		JPanel opengl_panel = new JPanel();
		opengl_panel.setBorder(BorderFactory.createTitledBorder("OpenGL Controls"));
		opengl_panel.setLayout(new BoxLayout(opengl_panel, BoxLayout.Y_AXIS));

		JCheckBox depth_test_toggle = new JCheckBox("Depth Test", false);
		depth_test_toggle.addActionListener(e -> enable_depth_test_ = ((JCheckBox) e.getSource()).isSelected());
		opengl_panel.add(depth_test_toggle);

		JCheckBox cull_face_toggle = new JCheckBox("Cull Faces", false);
		cull_face_toggle.addActionListener(e -> enable_face_cull_ = ((JCheckBox) e.getSource()).isSelected());
		opengl_panel.add(cull_face_toggle);

		JCheckBox cull_back_face = new JCheckBox("Cull Front Faces", false);
		cull_back_face.addActionListener(e -> cull_front_faces_ = ((JCheckBox) e.getSource()).isSelected());
		opengl_panel.add(cull_back_face);

		opengl_panel.add(new ComplexSlider("Color <-> Texture", 0, 1, color_texture_interpolation_, 0.01F,
				this::updateColorTextureInterpolation));

		add(opengl_panel);

		pack();
		setVisible(true);
	}

	void updateXRotation(float value)
	{
		x_rot_ = (float) Math.toRadians(value);
		updateModel();
	}

	void updateYRotation(float value)
	{
		y_rot_ = (float) Math.toRadians(value);
		updateModel();
	}

	void updateZRotation(float value)
	{
		z_rot_ = (float) Math.toRadians(value);
		updateModel();
	}

	void updateXTranslation(float value)
	{
		x_trans_ = value;
		updateModel();
	}

	void updateYTranslation(float value)
	{
		y_trans_ = value;
		updateModel();
	}

	void updateZTranslation(float value)
	{
		z_trans_ = value;
		updateModel();
	}

	void updateScale(float value)
	{
		scale_ = value;
		updateModel();
	}

	void updateXPos(float value)
	{
		view_pos_[0] = value;
		updateView();
	}

	void updateYPos(float value)
	{
		view_pos_[1] = value;
		updateView();
	}

	void updateZPos(float value)
	{
		view_pos_[2] = value;
		updateView();
	}

	void updateXLookAt(float value)
	{
		view_point_[0] = value;
		updateView();
	}

	void updateYLookAt(float value)
	{
		view_point_[1] = value;
		updateView();
	}

	void updateZLookAt(float value)
	{
		view_point_[2] = value;
		updateView();
	}

	void updatePerspectiveNear(float value)
	{
		perspective_near_ = value;
		updateProjection();
	}

	void updatePerspectiveFar(float value)
	{
		perspective_far_ = value;
		updateProjection();
	}

	void updateFOV(float value)
	{
		fov_ = value;
		updateProjection();
	}

	void updateOrhtographicNear(float value)
	{
		orthographic_near_ = value;
		updateProjection();
	}

	void updateOrhtographicFar(float value)
	{
		orthographic_far_ = value;
		updateProjection();
	}

	void updateLightRotation(float value)
	{
		light_rotation_ = value;
		updateLight();
	}

	void updateLightY(float value)
	{
		light_y_ = value;
		updateLight();
	}

	void updateModel()
	{
		float[][] center = MatrixUtils.Identity();

		float[][] rot_x = MatrixUtils.RotationX(x_rot_);
		float[][] rot_y = MatrixUtils.RotationY(y_rot_);
		float[][] rot_z = MatrixUtils.RotationZ(z_rot_);
		float[][] rot = Matrix.matMult(rot_z, Matrix.matMult(rot_y, rot_x));

		float[][] scale = MatrixUtils.Scale(scale_);

		float[][] back = MatrixUtils.Identity();

		float[][] trans = MatrixUtils.Translation(x_trans_, y_trans_, z_trans_);

		float[][] model = center;
		model = Matrix.matMult(scale, model);
		model = Matrix.matMult(rot, model);
		model = Matrix.matMult(back, model);
		model = Matrix.matMult(trans, model);

		model_ = LWJGLHelper.ConvertMat(model);
	}

	void updateView()
	{
		view_ = LWJGLHelper.LookAtMat(view_pos_, view_up_, view_point_);
	}

	void updateProjection()
	{
		if (use_perspective_projection_)
			projection_ = LWJGLHelper.Perspective(window_width_, window_height_, perspective_near_, perspective_far_,
					fov_);
		else
			projection_ = LWJGLHelper.Orthographic(-window_width_ / 2, window_width_ / 2, -window_height_ / 2,
					window_height_ / 2, orthographic_near_, orthographic_far_);
	}

	void updateLight()
	{
		light_ = new float[] {
			(float) Math.sin(Math.toRadians(light_rotation_)) * light_distance_,
			light_y_,
			(float) Math.cos(Math.toRadians(light_rotation_)) * light_distance_
		};
	}

	void updateOpenGLBindings()
	{
		if (enable_depth_test_)
			GL11.glEnable(GL11.GL_DEPTH_TEST);
		else
			GL11.glDisable(GL11.GL_DEPTH_TEST);

		if (enable_face_cull_)
			GL11.glEnable(GL11.GL_CULL_FACE);
		else
			GL11.glDisable(GL11.GL_CULL_FACE);

		if (cull_front_faces_)
			GL11.glCullFace(GL11.GL_FRONT);
		else
			GL11.glCullFace(GL11.GL_BACK);

	}

	void updateColorTextureInterpolation(float value)
	{
		color_texture_interpolation_ = value;
	}

	float[] getModel()
	{
		return model_;
	}

	float[] getView()
	{
		return view_;
	}

	float[] getProjection()
	{
		return projection_;
	}

	float[] getLight()
	{
		return light_;
	}

	float[] getCam()
	{
		return view_pos_;
	}

	float getColorTextureInterpolation()
	{
		return color_texture_interpolation_;
	}
}

class ComplexSlider extends JPanel
{
	// A Complex slider is basically just a normal slider, wrapped in a panel, that calls a specific function when
	//  it changes its value
	ComplexSlider(String text, int min, int max, int value, SliderChangeFunc func)
	{
		// Create a new titled border that displays the text and the current value
		setBorder(BorderFactory.createTitledBorder(text + ": " + value));
		setLayout(new BorderLayout());

		// Create the slider and add a change listener to it
		JSlider slider = new JSlider(min, max, value);
		slider.addChangeListener(evt ->
		{
			// Get the current value for the slider
			int currValue = ((JSlider) evt.getSource()).getValue();
			// Update the border text by creating a new border
			setBorder(BorderFactory.createTitledBorder(text + ": " + currValue));
			// And call the functional interface that was passed in the constructor with the current value
			func.call(currValue);
		});
		add(slider, BorderLayout.CENTER);
	}

	ComplexSlider(String text, float min, float max, float value, float scaling, SliderChangeFunc func)
	{
		setBorder(BorderFactory.createTitledBorder(String.format("%s: %.2f", text, value)));
		setLayout(new BorderLayout());

		// Create a new titled border that displays the text and the current value
		// Create the slider and add a change listener to it
		JSlider slider = new JSlider((int) (min / scaling), (int) (max / scaling), (int) (value / scaling));
		slider.addChangeListener(evt ->
		{
			// Get the current value for the slider
			float currValue = ((JSlider) evt.getSource()).getValue() * scaling;
			// Update the border text by creating a new border
			setBorder(BorderFactory.createTitledBorder(String.format("%s: %.2f", text, currValue)));
			// And call the functional interface that was passed in the constructor with the current value
			func.call(currValue);
		});
		add(slider, BorderLayout.CENTER);
	}
}

// A functinal interface that is used by the complex slider in ints change listener
interface SliderChangeFunc
{
	public void call(float value);
}

class MatrixUtils
{
	static float[][] RotationX(float value)
	{
		float[][] mat = Identity();

		float cos = (float) Math.cos(value);
		float sin = (float) Math.sin(value);

		mat[1][1] = cos;
		mat[1][2] = -sin;
		mat[2][1] = sin;
		mat[2][2] = cos;

		return mat;
	}

	static float[][] RotationY(float value)
	{
		float[][] mat = Identity();

		float cos = (float) Math.cos(value);
		float sin = (float) Math.sin(value);

		mat[0][0] = cos;
		mat[0][2] = sin;
		mat[2][0] = -sin;
		mat[2][2] = cos;

		return mat;
	}

	static float[][] RotationZ(float value)
	{
		float[][] mat = Identity();

		float cos = (float) Math.cos(value);
		float sin = (float) Math.sin(value);

		mat[0][0] = cos;
		mat[0][1] = -sin;
		mat[1][0] = sin;
		mat[1][1] = cos;

		return mat;
	}

	static float[][] TranslationX(float value)
	{
		float[][] mat = Identity();
		mat[0][3] = value;
		return mat;
	}

	static float[][] TranslationY(float value)
	{
		float[][] mat = Identity();
		mat[1][3] = value;
		return mat;
	}

	static float[][] TranslationZ(float value)
	{
		float[][] mat = Identity();
		mat[2][3] = value;
		return mat;
	}

	static float[][] Translation(float x, float y, float z)
	{
		float[][] mat = Identity();
		mat[0][3] = x;
		mat[1][3] = y;
		mat[2][3] = z;
		return mat;
	}

	static float[][] Scale(float value)
	{
		float[][] mat = new float[4][4];
		mat[3][3] = 1.0F;
		for (int i = 0; i < 3; ++i)
			mat[i][i] = value;
		return mat;
	}

	static float[][] Identity()
	{
		float[][] mat = new float[4][4];
		for (int i = 0; i < 4; ++i)
			mat[i][i] = 1.0F;
		return mat;
	}
}