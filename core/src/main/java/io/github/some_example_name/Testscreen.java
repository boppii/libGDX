package io.github.some_example_name;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.ScreenUtils;

public class Testscreen implements Screen
{
	Main game;
	
	public Testscreen(Main game) {
		this.game = game;
	}
	
	@Override
	public void show()
	{
	
	}
	
	
	@Override
	public void render(float delta) {
		ScreenUtils.clear(0, 0, 0, 1, true);
		game.testbuffer.begin();
		ScreenUtils.clear(0, 0, 0, 1, true);
		game.viewport.apply();
		game.batch.setProjectionMatrix(game.viewport.getCamera().combined);
		
		
		game.batch.begin();
		game.font.draw(game.batch, "Helloooooooooo",100,100);
		game.batch.end();
		game.testbuffer.end();
		
		if (Gdx.input.isKeyPressed(Input.Keys.Q)) {
			game.setScreen(new Mainscreen(game));
		}
	
	
	
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
