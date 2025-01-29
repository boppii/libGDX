package io.github.some_example_name;

public class Mainscreen implements com.badlogic.gdx.Screen{
	Main game;
	
	public Mainscreen(Main game){
		this.game = game;
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
		game.testbuffer.begin();
		game.batch.begin();
		
		
		game.makebox(test1, test2);
		game.makebox(test3,test4);
		game.font.draw(game.batch, "test string", 400, 100);
		game.font.draw(game.batch, "press 'Q' to test if i did screen right", 200,200);
		
		game.batch.end();
		game.testbuffer.end();
	}
	
	@Override
	public void show()
	{
	
	}
	
	@Override
	public void render(float delta)
	{
		framebuffer();
	}
	
	@Override
	public void resize(int width, int height)
	{
		game.viewport.update(width, height, true);
	}
	
	@Override
	public void pause()
	{
	
	}
	
	@Override
	public void resume()
	{
	
	}
	
	@Override
	public void hide()
	{
	
	}
	
	@Override
	public void dispose()
	{
	
	}
}
