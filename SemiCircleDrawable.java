public class SemiCircleDrawable extends Drawable {

    private Paint paint;
    private RectF rectF;
    private int fillColor;
    private int strokeColor;
    private float strokeWidth;

    private Direction angle;

    public enum Direction {
        LEFT,
        RIGHT,
        TOP,
        BOTTOM
    }

    public SemiCircleDrawable(@ColorInt int fillColor, @ColorInt int strokeColor, float strokeWidth, Direction angle) {
        this.fillColor = fillColor;
        this.strokeColor = strokeColor;
        this.strokeWidth = strokeWidth;
        this.angle = angle;
        paint = new Paint();
        paint.setAntiAlias(true);
        rectF = new RectF();
    }


    @Override
    public void draw(Canvas canvas) {
        Rect bounds = getBounds();
        float radius;
        if (angle == Direction.LEFT || angle == Direction.RIGHT) {
            radius = Math.min(bounds.width(), bounds.height() / 2) - strokeWidth;
            if (angle == Direction.RIGHT) {
                rectF.set(bounds.right-strokeWidth-2*radius, bounds.centerY() - radius, bounds.right-strokeWidth, bounds.centerY() + radius);
            } else {
                rectF.set(bounds.left + strokeWidth, bounds.centerY() - radius, bounds.left + strokeWidth + 2 * radius, bounds.centerY() + radius);
            }
        } else {
            radius = Math.min(bounds.width() / 2, bounds.height()) - strokeWidth;
            if (angle == Direction.BOTTOM) {
                rectF.set(bounds.centerX() - radius, bounds.bottom - strokeWidth - 2 * radius, bounds.centerX() + radius, bounds.bottom - strokeWidth);
            } else {
                rectF.set(bounds.centerX() - radius, bounds.top + strokeWidth, bounds.centerX() + radius, bounds.top + strokeWidth + 2 * radius);
            }
        }


        //draw solid
        paint.setColor(fillColor);
        paint.setStyle(Paint.Style.FILL);
        if (angle == Direction.LEFT)
            canvas.drawArc(rectF, 90, 180, true, paint);
        else if (angle == Direction.TOP)
            canvas.drawArc(rectF, 180, 180, true, paint);
        else if (angle == Direction.RIGHT)
            canvas.drawArc(rectF, 270, 180, true, paint);
        else if (angle == Direction.BOTTOM)
            canvas.drawArc(rectF, 0, 180, true, paint);


        //draw stroke
        paint.setColor(strokeColor);
        paint.setStrokeWidth(strokeWidth);
        paint.setStyle(Paint.Style.STROKE);
        if (angle == Direction.LEFT)
            canvas.drawArc(rectF, 90, 180, false, paint);
        else if (angle == Direction.TOP)
            canvas.drawArc(rectF, 180, 180, false, paint);
        else if (angle == Direction.RIGHT)
            canvas.drawArc(rectF, 270, 180, false, paint);
        else if (angle == Direction.BOTTOM)
            canvas.drawArc(rectF, 0, 180, false, paint);
    }

    @Override
    public void setAlpha(int alpha) {
        // Has no effect
    }

    @Override
    public void setColorFilter(ColorFilter cf) {
        // Has no effect
    }

    @Override
    public int getOpacity() {
        // Not Implemented
        return 0;
    }

}