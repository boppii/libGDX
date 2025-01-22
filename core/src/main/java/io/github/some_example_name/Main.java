package io.github.some_example_name;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
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
    private int[] WorldWH = new int[]{640, 480};
    
    
    
    
    @Override
    public void create() {
        batch = new SpriteBatch();

        // Framebuffer init. (pls only make max of 2 i dont want java to memory leak because i forgot to do something)
        testbuffer = new FrameBuffer(Pixmap.Format.RGBA8888, WorldWH[0], WorldWH[1], false);


        shapeRender = new ShapeRenderer();

        //TODO figure out what viewport actually is
        viewport = new FitViewport(WorldWH[0], WorldWH[1]);


        // Actually working spritesheet, finally. Fuck monogame, i got this shit implemented in like 3 hrs instead of like 4 days
        atlas = new TextureAtlas("E:/LibGDX/Testgame/assets/atlas/box.atlas");
        wall = new Sprite(atlas.findRegion("wall"));
        corner = new Sprite(atlas.findRegion("corner"));





    }

    @Override
    public void resize(int width, int height){
        viewport.update(width, height,true);// Its in the doc idk what its actually for ¯\_(ツ)_/¯
    }


    @Override
    public void render(){
        //resize(WorldWH[0],WorldWH[1]);

        //boxWH
        int[] boxWH = new int[]{10,10};
        //boxPos
        int[] boxPos = new int[]{50,50};

        testbuffer.begin();
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);// This makes it work somehow, i hate openGL (fucking transparencies)


        batch.begin();

        makebox(boxWH,boxPos);

        batch.end();
        testbuffer.end();

        //don't touch these
        input();
        logic();
        draw();
    }


    public void input(){

    }

    public void logic(){

    }


    public void draw(){
        viewport.apply();
        batch.setProjectionMatrix(viewport.getCamera().combined);
        buffer = testbuffer.getColorBufferTexture();
        batch.begin();
        batch.draw(buffer,0,0);
        batch.end();

    }

    @Override
    public void dispose() 
    {
        batch.dispose();
        //image.dispose();
        buffer.dispose();
        testbuffer.dispose();
        atlas.dispose();
        shapeRender.dispose();

    }

    public void makebox(int[] boxWH, int[] boxPos){
        //TODO actually make this bullshit again, dont call for begin or end just assume your already being drawn

        //boxbuffer = new FrameBuffer(Pixmap.Format.RGBA8888,boxWH[0],boxWH[1], false);
        //boxbuffer.begin();

        //batch.begin();
        //Gdx.gl.glClearColor(0, 0, 0, 1);
        //Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        wall.draw(batch);
        wall.setBounds(boxPos[0], boxPos[1], 50,10);


        //batch.end();
        //boxbuffer.end();



        corner.setBounds(50,20,20,20);


    }



}




