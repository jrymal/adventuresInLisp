package com.rymal.games.arena.ui.gameui;

import com.rymal.games.arena.R;
import com.rymal.games.arena.engine.GameEngine;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

public class GameView extends View {

	private GameEngine _engine;
	private Paint _fgPaint;
	private Paint _bgPaint;
	
	private float _lastTouchX;
	private float _lastTouchY;
	
	private static enum Location {
		HAND,
		NEXT_PHASE
	}
	
	public GameView(Context context) {
		super(context);
		
		_fgPaint = new Paint();
		_fgPaint.setColor(Color.RED);
		_fgPaint.setAntiAlias(true);
		_fgPaint.setTextSize(30f);
		
		_bgPaint = new Paint();
		_bgPaint.setColor(Color.LTGRAY);
		setBackgroundColor(Color.LTGRAY);
	}

	public void setEngine(GameEngine gameEngine) {
		_engine = gameEngine;
	}
	
	@Override
	public void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		canvas.save();
		
		int height = canvas.getHeight();
		int width = canvas.getWidth();
		
		canvas.drawRect(0, 0, width, height, _bgPaint);
		
		int posX = 15;
		int posY = 50;
		
		canvas.drawText(
				getContext().getString(R.string.item_gameui_round)+_engine.getCurrentRound(), 
				posX, posY,  _fgPaint);
		
		posY += 40;
		canvas.drawText(
				getContext().getString(R.string.item_gameui_phase)+_engine.getCurrentPhase().name(), 
				posX, posY,  _fgPaint);
		
		posY += 40;
		canvas.drawText(
				getContext().getString(R.string.item_gameui_player)+_engine.getCurrentPlayer().getName(), 
				posX, posY,  _fgPaint);
		
		posY += 40;
		canvas.drawText(
				getContext().getString(R.string.item_gameui_cash)+_engine.getCurrentPlayer().getTokensLeft(), 
				posX, posY,  _fgPaint);

		posY += 40;
		canvas.drawText(
				_lastTouchX +" " + _lastTouchY, 
				posX, posY,  _fgPaint);
		
		canvas.restore();
	}
	
    @Override
    public boolean onTouchEvent (MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN :
            	float x = event.getX(0);
            	float y = event.getY(0);
            	
            	Location loc = getLocation(x, y);
            	switch(loc) {
            	
            	}
            	
            	_engine.phaseDone();
                invalidate();
                break;
        }

        return true;

    }

	private Location getLocation(float x, float y) {
		_lastTouchX = x;
		_lastTouchY = y;
		
		
		return Location.NEXT_PHASE;
	}

	
}
