package com.example.adicional;
import static android.content.ContentValues.TAG;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.graphics.Path;

public class ProgressCanvasView extends View {

    private Paint paint;
    private int progressCount = 0;
    private static final int MAX_PROGRESS = 5;
    private int[] colors = {Color.parseColor("#FFFFFF"), Color.parseColor("#FFE0B2"), Color.parseColor("#FFB74D"), Color.parseColor("#FFA726"), Color.parseColor("#FF9800")};  // Degradado de blanco a naranja
    private int arrowSpacing;  // Espacio entre flechas

    public ProgressCanvasView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ProgressCanvasView(Context context) {
        super(context);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setStrokeWidth(5);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        arrowSpacing = -120; 
    }

    public void advanceProgress() {
        if (progressCount < MAX_PROGRESS) {
            progressCount++;
            invalidate();  // Redraw the view
        }
    }

    public void resetProgress() {
        progressCount = 0;
        invalidate();  // Redraw the view
        Log.d(TAG, "Se ha reiniciado el progreso."); // Agrega un mensaje de log
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = getWidth();
        int height = getHeight();

        // Ajustando el tamaño de la flecha y la separación entre ellas
        int arrowWidth = (width - arrowSpacing * (MAX_PROGRESS - 1)) / MAX_PROGRESS;
        int arrowHeight = height / 2;

        // Ajustando la inclinación de las flechas
        int arrowIncline = arrowHeight / 2;  // Ajusta este valor para cambiar la inclinación de las flechas

        for (int i = 0; i < progressCount; i++) {
            int startX = i * (arrowWidth + arrowSpacing);
            int endX = startX + arrowWidth;

            paint.setColor(colors[i]);

            // Dibujar flecha rellena
            canvas.drawPath(createArrowPath(startX, endX, arrowIncline), paint);

            // Dibujar borde de la flecha (blanco)
            paint.setColor(Color.WHITE);
            paint.setStyle(Paint.Style.STROKE);
            canvas.drawPath(createArrowPath(startX, endX, arrowIncline), paint);
            paint.setStyle(Paint.Style.FILL_AND_STROKE);
        }
    }

    private Path createArrowPath(int startX, int endX, int arrowIncline) {
        Path path = new Path();
        int arrowTipLength = (endX - startX) / 2;  // Longitud de la punta de la flecha, ajustable para la agudeza

        path.moveTo(startX, arrowIncline);
        path.lineTo(endX - arrowTipLength, arrowIncline);
        path.lineTo(endX, arrowIncline + arrowIncline);
        path.lineTo(endX - arrowTipLength, arrowIncline + arrowIncline + arrowIncline);
        path.lineTo(startX, arrowIncline + arrowIncline + arrowIncline);
        path.lineTo(startX + arrowTipLength, arrowIncline + arrowIncline);
        path.close();

        return path;
    }
}
