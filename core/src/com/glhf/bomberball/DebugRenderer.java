package com.glhf.bomberball;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/*
    class DebugRenderer

    Classe permettant d'afficher des éléments de debug sur la fenêtre
*/
public class DebugRenderer {

    private SpriteBatch batch;
    private float width;
    private float height;
    private ShapeRenderer shapeRenderer;

    public DebugRenderer(SpriteBatch batch)
    {
        this.batch = batch;
        width = Constants.APP_WIDTH;
        height = Constants.APP_HEIGHT;
        shapeRenderer = new ShapeRenderer();
    }

    // Affiche n lignes verticales et n lignes horizontales
    public void drawLines(int n)
    {
        float dh = height / n;
        float dw = width / n;
        for (int y = 0; y < n; y++)
            DrawDebugLine(0, y * dh, width, y * dh);
        for (int x = 0; x < n; x++)
            DrawDebugLine(x * dw, 0, x * dw, height);
    }

    private void DrawDebugLine(float x_0, float y_0, float x_1, float y_1)
    {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.BLACK); // Red line
        shapeRenderer.line(x_0, y_0, x_1, y_1);
        shapeRenderer.end();
    }
}
