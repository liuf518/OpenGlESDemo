package com.example.openglesdemo;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLSurfaceView.Renderer;
import android.opengl.GLU;
import android.util.Log;

public class OpenGLRenderer implements Renderer {
	private Square square = new Square();
	private Float angle = 15f;
	
	private Plane plane = new Plane();
	
	private Cube cube = new Cube(2, 2, 2);
	
	private Polyhedron polyhedron = new Polyhedron();
	
	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		Log.e("", "coolf entry onSurfaceCreated");
		// set the background color to black (rgba)
		gl.glClearColor(0.0f, 0.0f, 0.0f, 0.5f);
		//enable smooth shading,default not really needed
		gl.glShadeModel(GL10.GL_SMOOTH);
		//depth buffer setup
		gl.glClearDepthf(1.0f);
		//enables depth testing
		gl.glEnable(GL10.GL_DEPTH_TEST);
		//the type of depth testing to do 
		gl.glDepthFunc(GL10.GL_LEQUAL);
		//really nice perspective calculations
		gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_NICEST);
	}
	
	@Override
	public void onDrawFrame(GL10 gl) {
		Log.e("", "coolf entry onDrawFrame angle = " + angle);
		//clears the screen and depth buffer
//		Float angle = 15f;
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
		gl.glLoadIdentity();
		gl.glTranslatef(0, 0, -15);
//		
		//square a
		gl.glPushMatrix();
		gl.glRotatef(angle, 0, 1, 0);
		polyhedron.draw(gl);
		gl.glPopMatrix();
		
		//square b
		gl.glPushMatrix();
		gl.glRotatef(-angle, 0, 1, 0);
		gl.glTranslatef(2, 0, 0);
		gl.glScalef(0.5f, 0.5f, 0.5f);
		polyhedron.draw(gl);

		gl.glPushMatrix();
		gl.glRotatef(-angle, 0, 1, 0);
		gl.glTranslatef(2, 0, 0);
		gl.glScalef(0.5f, 0.5f, 0.5f);
		gl.glRotatef(angle * 10, 0, 1, 0);
		polyhedron.draw(gl);
		
		gl.glPopMatrix();
		gl.glPopMatrix();
		
//		gl.glRotatef(angle, 0, 1, 0);
//		polyhedron.draw(gl);
		
		angle ++;
		
//		cube.draw(gl);
	}

	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		//sets the current view port to the new size
		gl.glViewport(0, 0, width, height);
		//select the projection matrix
		gl.glMatrixMode(GL10.GL_PROJECTION);
		//reset the projection matrix
		gl.glLoadIdentity();
		//calculate the aspect ratio of the window
		GLU.gluPerspective(gl, 45.0f, (float)width/(float)height, 0.1f, 100.0f);
		//select the modelview matrix
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		//reset the modelview matrix
		gl.glLoadIdentity();
	}
}
