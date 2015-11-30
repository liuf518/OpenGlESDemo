package com.example.openglesdemo;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.opengles.GL10;

public class Square {
	//our vertices
	private float vertices[] = {
			-1.0f,1.0f,0.0f,  //0, top left
			-1.0f,-1.0f,0.0f, //1, bottom left
			1.0f,-1.0f,0.0f,  //2, bottom right
			1.0f,1.0f,0.0f,   //3, top right
	};
	
	//the order we like to connect them 
	private short[] indices = {0,1,2,0,2,3};
	
	//the colors mapped to the vertices
	float[] colors = {
		1.0f,0.0f,0.0f,1.0f,
		0.0f,1.0f,0.0f,1.0f,
		0.0f,0.0f,1.0f,1.0f,
		1.0f,0.0f,1.0f,1.0f,
	};
	
	//our vertex buffer
	private FloatBuffer vertexBuffer;
	
	//our index buffer
	private ShortBuffer indexBuffer;
	
	//our color buffer
	private FloatBuffer colorBuffer;
	
	public Square() {
		//a float is 4 bytes,therefore we multiply the number if vertices wiht 4
		ByteBuffer vbb = ByteBuffer.allocateDirect(vertices.length * 4);
		vbb.order(ByteOrder.nativeOrder());
		vertexBuffer = vbb.asFloatBuffer();
		vertexBuffer.put(vertices);
		vertexBuffer.position(0);
		
		//short is 2 btyes, therefore we multiply the number if vertices with 2
		ByteBuffer ibb = ByteBuffer.allocateDirect(indices.length * 2);
		ibb.order(ByteOrder.nativeOrder());
		indexBuffer = ibb.asShortBuffer();
		indexBuffer.put(indices);
		indexBuffer.position(0);
		
		ByteBuffer cbb = ByteBuffer.allocateDirect(colors.length * 4);
		cbb.order(ByteOrder.nativeOrder());
		colorBuffer = cbb.asFloatBuffer();
		colorBuffer.put(colors);
		colorBuffer.position(0);
	}
	
	/**
	 * This function draw our square on screen
	 */
	public void drawSmoothColor(GL10 gl) {
		gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
		gl.glColorPointer(4, GL10.GL_FLOAT, 0, colorBuffer);
		//counter-clockwise winding
		gl.glFrontFace(GL10.GL_CCW);
		//enable face culling
		gl.glEnable(GL10.GL_CULL_FACE);
		//what faces to remove with the face culling
		gl.glCullFace(GL10.GL_BACK);
		
		//enabled the vertices buffer for writing and to be used during rendering
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		//specifies the location and data format of an array of vertex 
		//coordinates to use when rendering
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);
		
		gl.glDrawElements(GL10.GL_TRIANGLES, indices.length, GL10.GL_UNSIGNED_SHORT, indexBuffer);
//		
//		//disable the vertices buffer
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
//		//disable face culling
		gl.glDisable(GL10.GL_CULL_FACE);
		
		
		gl.glDisableClientState(GL10.GL_COLOR_ARRAY);
	}
	
	public void drawColor(GL10 gl) {
		gl.glColor4f(0.5f, 0.5f, 1.0f, 1.0f);

		//counter-clockwise winding
		gl.glFrontFace(GL10.GL_CCW);
		//enable face culling
		gl.glEnable(GL10.GL_CULL_FACE);
		//what faces to remove with the face culling
		gl.glCullFace(GL10.GL_BACK);
		
		//enabled the vertices buffer for writing and to be used during rendering
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		//specifies the location and data format of an array of vertex 
		//coordinates to use when rendering
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);
		
		gl.glDrawElements(GL10.GL_TRIANGLES, indices.length, GL10.GL_UNSIGNED_SHORT, indexBuffer);
//		
//		//disable the vertices buffer
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
//		//disable face culling
		gl.glDisable(GL10.GL_CULL_FACE);
	}
	
	public void draw(GL10 gl) {
		//counter-clockwise winding
		gl.glFrontFace(GL10.GL_CCW);
		//enable face culling
		gl.glEnable(GL10.GL_CULL_FACE);
		//what faces to remove with the face culling
		gl.glCullFace(GL10.GL_BACK);
		
		//enabled the vertices buffer for writing and to be used during rendering
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		//specifies the location and data format of an array of vertex 
		//coordinates to use when rendering
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);
		
		gl.glDrawElements(GL10.GL_TRIANGLES, indices.length, GL10.GL_UNSIGNED_SHORT, indexBuffer);
//		
//		//disable the vertices buffer
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
//		//disable face culling
		gl.glDisable(GL10.GL_CULL_FACE);
	}
}
