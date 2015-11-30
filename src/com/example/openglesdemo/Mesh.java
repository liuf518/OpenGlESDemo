package com.example.openglesdemo;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.opengles.GL10;

public class Mesh {
	private FloatBuffer verticesBuffer = null;
	private ShortBuffer indicesBuffer = null;
	private int numofIndices = -1;
	private float[] rgba = new float[] {1.0f,0.0f,0.0f,1.0f};
	private FloatBuffer colorBuffer = null;
	
	private float x = 0;
	private float y = 0;
	private float z = 0;
	private float rx = 0;
	private float ry = 0;
	private float rz = 0;
	
	public void draw(GL10 gl) {
		gl.glFrontFace(GL10.GL_CCW);
		gl.glEnable(GL10.GL_CULL_FACE);
		gl.glCullFace(GL10.GL_BACK);
		
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, verticesBuffer);
		
		gl.glColor4f(rgba[0], rgba[1], rgba[2], rgba[3]);
		
		if (colorBuffer != null) {
			gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
			gl.glColorPointer(4, GL10.GL_FLOAT, 0, colorBuffer);
		}
		
		gl.glTranslatef(x, y, z);
		gl.glRotatef(rx, 1, 0, 0);
		gl.glRotatef(ry, 0, 1, 0);
		gl.glRotatef(rz, 0, 1, 0);
		
		gl.glDrawElements(GL10.GL_TRIANGLES, numofIndices, GL10.GL_UNSIGNED_SHORT, indicesBuffer);
		gl.glDisable(GL10.GL_VERTEX_ARRAY);
		gl.glDisable(GL10.GL_CULL_FACE);
	}
	
	protected void setVertices(float[] vertices) {
		ByteBuffer vbb = ByteBuffer.allocateDirect(vertices.length * 4);
		vbb.order(ByteOrder.nativeOrder());
		verticesBuffer = vbb.asFloatBuffer();
		verticesBuffer.put(vertices);
		verticesBuffer.position(0);
	}
	
	protected void setIndices(short[] indices) {
		ByteBuffer ibb = ByteBuffer.allocateDirect(indices.length * 2);
		ibb.order(ByteOrder.nativeOrder());
		indicesBuffer = ibb.asShortBuffer();
		indicesBuffer.put(indices);
		indicesBuffer.position(0);
	}
	
	protected void setColor(float red,float green,float blue,float alpha) {
		rgba[0] = red;
		rgba[1] = green;
		rgba[2] = blue;
		rgba[3] = alpha;
	}
	
	protected void setColors(float[] colors) {
		ByteBuffer cbb = ByteBuffer.allocateDirect(colors.length * 4);
		cbb.order(ByteOrder.nativeOrder());
		colorBuffer = cbb.asFloatBuffer();
		colorBuffer.put(colors);
		colorBuffer.position(0);
	}
}
