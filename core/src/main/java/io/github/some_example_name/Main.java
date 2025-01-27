package io.github.some_example_name;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;


/**
 * {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms.
 */
public class Main extends ApplicationAdapter
{
	
	private SpriteBatch batch;
	//private Texture image;
	private Texture buffer;
	private FrameBuffer testbuffer;
	private ShapeRenderer shapeRender;
	private FitViewport viewport;
	private Sprite wall;
	private Sprite corner;
	private TextureAtlas atlas;
	private FreeTypeFontGenerator generator;
	private FreeTypeFontGenerator.FreeTypeFontParameter parameter;
	private BitmapFont font;
	private int[] WorldWH = new int[]{640, 480};
	
	
	@Override
	public void create()
	{
		batch = new SpriteBatch();
		
		// Framebuffer init. (pls only make max of 2 i dont want java to memory leak because i forgot to do something)
		testbuffer = new FrameBuffer(Pixmap.Format.RGBA8888, WorldWH[0], WorldWH[1], false);
		
		
		shapeRender = new ShapeRenderer();
		
		//TODO figure out what viewport actually is
		viewport = new FitViewport(WorldWH[0], WorldWH[1]);
		
		
		// Actually working spritesheet, finally. Fuck monogame, i got this shit implemented in like 3 hrs instead of like 4 days
		atlas = new TextureAtlas("atlas/box.atlas"); wall = new Sprite(atlas.findRegion("wall"));
		corner = new Sprite(atlas.findRegion("corner"));
		
		// font
		generator = new FreeTypeFontGenerator(Gdx.files.internal("font/HasklugNerdFontMono-Medium.otf"));
		parameter = new FreeTypeFontGenerator.FreeTypeFontParameter(); //why is it doing this??
		parameter.size = 12; font = generator.generateFont(parameter); // font size 12 pixels
		
		//init here so it doesnt rotate constantly
		framebuffer();
		
		
	}
	
	public void framebuffer()
	{
		//resize(WorldWH[0],WorldWH[1]);
		
		//boxWH
		int[] boxWH = new int[]{625,295};
		
		//boxPos                > 7,20 < dont go lower than these values!!!,
		int[] boxPos = new int[]{7,304};//it draws the leftmost part of the top line at these values.
										//going lower will make part or all of the box be off-screen.
		int[] test1 = new int[]{305,150};
		int[] test2 = new int[]{327,471};
		int[] test3 = new int[]{305,150};
		int[] test4 = new int[]{7,471};
		testbuffer.begin();
		batch.begin();
		
		makebox(boxWH, boxPos);
		makebox(test1, test2);
		makebox(test3,test4);
		font.draw(batch, "test string", 400, 100);
		
		batch.end();
		testbuffer.end();
	}
	
	@Override
	public void resize(int width, int height)
	{
		viewport.update(width, height, true);// Its in the doc idk what its actually for ¯\_(ツ)_/¯
	}
	
	
	@Override
	public void render()
	{
		
		
		//don't touch these
		input(); logic(); draw();
	}
	
	public void input()
	{
	
	}
	
	public void logic()
	{
	
	}
	
	
	public void draw()
	{
		
		viewport.apply();
		batch.setProjectionMatrix(viewport.getCamera().combined);
		buffer = testbuffer.getColorBufferTexture();
		TextureRegion flip = new TextureRegion(buffer);
		flip.flip(false, true);// inverts framebuffer to the right way up
		
		batch.begin();
		batch.draw(flip, 0, 0);
		batch.end();
		
	}
	
	@Override
	public void dispose()
	{
		batch.dispose();
		//image.dispose();
		buffer.dispose(); testbuffer.dispose(); atlas.dispose(); shapeRender.dispose(); generator.dispose();
		font.dispose();
		
	}
	
	
	
	
	public void makebox(int[] boxWH, int[] boxPos)
	{
		//It's done. It's garbage but it works. Nobody ask me what numbers are actually being used here
		//because I dont know myself. All I can tell you is that "wall.getX / wall.getX" is giving a 1
		//as the result. This will only ever work for PoT sprites BTW... Maybe, I haven't tested that yet.
		int boxPosX = 0;
		int boxPosY = 0;
		
		boxPosX = boxPos[0];
		boxPosY = boxPos[1];
		
		int boxPosXtmp = boxPosX;
		int boxPosYtmp = boxPosY;
		
		int boxW = boxWH[0];
		int boxH = boxWH[1];
		
		int boxWtmp = boxW;
		int boxHtmp = boxH;//i didnt event need these? maybe keep for when i do
		
		wall.setRotation(0);//setup so it works for multiple calls(clearing values)
		corner.setFlip(false,false);
		
		
		
		for (int i = 0; i < boxW; i++) {
			wall.setPosition(boxPosXtmp, boxPosYtmp);
			wall.draw(batch);
			boxPosXtmp++;
		}
		
		
		corner.setPosition(boxPosXtmp,boxPosYtmp);
		corner.draw(batch);
		
		boxPosYtmp = (int) (boxPosYtmp - (corner.getHeight() - wall.getWidth() - (wall.getWidth() / wall.getWidth())));
		boxPosXtmp = (int) (boxPosXtmp + (wall.getWidth() + (wall.getWidth() / wall.getWidth())));//ITS ALWAYS OFF BY 1 AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
		
		wall.setRotation(90);
		for (int i = 0; i < boxH; i++) {
			
			wall.setPosition(boxPosXtmp, boxPosYtmp);
			wall.draw(batch);
			boxPosYtmp--;
		}
		
		
		boxPosYtmp = (int) (boxPosYtmp - (corner.getHeight() - wall.getWidth() - wall.getWidth()));
		boxPosXtmp = (int) (boxPosXtmp - (wall.getWidth() + wall.getWidth() - (wall.getWidth() / wall.getWidth())));
		
		
		corner.flip(false,true);
		corner.setPosition(boxPosXtmp,boxPosYtmp);
		corner.draw(batch);
		
		
		wall.setRotation(180);
		for (int i = 0; i < boxW; i++) {
			wall.setPosition(boxPosXtmp, boxPosYtmp);
			wall.draw(batch);
			boxPosXtmp--;
			
		}
		
		
		boxPosYtmp = (int) (boxPosYtmp - (corner.getWidth() % wall.getWidth()));
		boxPosXtmp = (int) (boxPosXtmp - (corner.getHeight() - (wall.getWidth() / wall.getWidth()) ));
		
		
		corner.flip(true,false);
		corner.setPosition(boxPosXtmp,boxPosYtmp);
		corner.draw(batch);
		
		boxPosYtmp = (int) (boxPosYtmp + (corner.getWidth() - wall.getWidth() - (wall.getHeight() / wall.getHeight())));
		boxPosXtmp = (int) (boxPosXtmp + (corner.getWidth() / wall.getHeight() + wall.getWidth()));
		
		
		wall.setRotation(270);
		for (int i = 0; i < boxH; i++) {
			wall.setPosition(boxPosXtmp, boxPosYtmp);
			wall.draw(batch);
			boxPosYtmp++;
		}
		
		
		boxPosYtmp = (int) (boxPosYtmp + (wall.getWidth() + wall.getWidth()));
		boxPosXtmp = (int) (boxPosXtmp - (wall.getWidth() + wall.getWidth() - (wall.getWidth() / wall.getWidth())));
		
		
		corner.flip(false,true);
		corner.setPosition(boxPosXtmp,boxPosYtmp);
		corner.draw(batch);
		
		corner.flip(false,false);
		wall.setRotation(0);

		
	}
	
	
}




