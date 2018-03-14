/*
 * Copyright 2015 AndroidPlot.com
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.androidplot.ui.widget;

import android.graphics.*;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.androidplot.exception.PlotRenderException;
import com.androidplot.ui.*;
import com.androidplot.util.DisplayDimensions;
import com.androidplot.ui.HorizontalPositioning;
import com.androidplot.ui.VerticalPositioning;
import com.androidplot.util.PixelUtils;

/**
 * A Widget is a graphical sub-element of a Plot that can be positioned relative
 * to the bounds of the Plot.
 */
public abstract class Widget implements BoxModelable, Resizable {

    private Paint borderPaint;
    private Paint backgroundPaint;
    private boolean clippingEnabled = false;
    private BoxModel boxModel = new BoxModel();
    private Size size;
    private DisplayDimensions plotDimensions = new DisplayDimensions();
    private DisplayDimensions widgetDimensions = new DisplayDimensions();
    private boolean isVisible = true;
    private PositionMetrics positionMetrics;
    private LayoutManager layoutManager;

    private Rotation rotation = Rotation.NONE;
    private RectF lastWidgetRect = null;

    public enum Rotation {
        NINETY_DEGREES,
        NEGATIVE_NINETY_DEGREES,
        ONE_HUNDRED_EIGHTY_DEGREES,
        NONE,
    }

    public Widget(LayoutManager layoutManager, SizeMetric heightMetric, SizeMetric widthMetric) {
        this(layoutManager, new Size(heightMetric, widthMetric));
    }

    public Widget(LayoutManager layoutManager, Size size) {
        this.layoutManager = layoutManager;
        Size oldSize = this.size;
        setSize(size);
        onMetricsChanged(oldSize, size);
    }

    public DisplayDimensions getWidgetDimensions() {
        return widgetDimensions;
    }

    public Anchor getAnchor() {
        return getPositionMetrics().getAnchor();
    }

    public void setAnchor(Anchor anchor) {
        getPositionMetrics().setAnchor(anchor);
    }


    /**
     * Same as {@link #position(float, HorizontalPositioning, float, VerticalPositioning, Anchor)}
     * but with the anchor parameter defaulted to the upper left corner.
     *
     * @param x
     * @param horizontalPositioning
     * @param y
     * @param verticalPositioning
     */
    public void position(float x, HorizontalPositioning horizontalPositioning, float y, VerticalPositioning verticalPositioning) {
        position(x, horizontalPositioning, y, verticalPositioning, Anchor.LEFT_TOP);
    }

    /**
     * @param x                     X-Coordinate of the top left corner of element.  When using RELATIVE, must be a value between 0 and 1.
     * @param horizontalPositioning LayoutType to use when orienting this element's X-Coordinate.
     * @param y                     Y_VALS_ONLY-Coordinate of the top-left corner of element.  When using RELATIVE, must be a value between 0 and 1.
     * @param verticalPositioning   LayoutType to use when orienting this element's Y_VALS_ONLY-Coordinate.
     * @param anchor                The point of reference used by this positioning call.
     */
    public void position(float x, HorizontalPositioning horizontalPositioning, float y,
                         VerticalPositioning verticalPositioning, Anchor anchor) {
        setPositionMetrics(new PositionMetrics(x, horizontalPositioning, y, verticalPositioning, anchor));
        layoutManager.addToTop(this);
    }

    /**
     * Can be overridden by subclasses to respond to resizing events.
     *
     * @param oldSize
     * @param newSize
     */
    protected void onMetricsChanged(Size oldSize, Size newSize) {
    }

    /**
     * Can be overridden by subclasses to handle any final resizing etc. that
     * can only be done after XML configuration etc. has completed.
     */
    public void onPostInit() {
    }

    /**
     * Determines whether or not point lies within this Widget.
     *
     * @param point
     * @return
     */
    public boolean containsPoint(PointF point) {
        return widgetDimensions.canvasRect.contains(point.x, point.y);
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public Size getSize() {
        return this.size;
    }


    public void setWidth(float width) {
        size.getWidth().setValue(width);
    }

    public void setWidth(float width, SizeMode layoutType) {
        size.getWidth().set(width, layoutType);
    }

    public void setHeight(float height) {
        size.getHeight().setValue(height);
    }

    public void setHeight(float height, SizeMode layoutType) {
        size.getHeight().set(height, layoutType);
    }

    public SizeMetric getWidthMetric() {
        return size.getWidth();
    }

    public SizeMetric getHeightMetric() {
        return size.getHeight();
    }

    public float getWidthPix(float size) {
        return this.size.getWidth().getPixelValue(size);
    }

    public float getHeightPix(float size) {
        return this.size.getHeight().getPixelValue(size);
    }

    public RectF getMarginatedRect(RectF widgetRect) {
        return boxModel.getMarginatedRect(widgetRect);
    }

    public RectF getPaddedRect(RectF widgetMarginRect) {
        return boxModel.getPaddedRect(widgetMarginRect);
    }

    @Override
    public void setMarginRight(float marginRight) {
        boxModel.setMarginRight(marginRight);
    }

    @Override
    public void setMargins(float left, float top, float right, float bottom) {
        boxModel.setMargins(left, top, right, bottom);
    }

    @Override
    public void setPadding(float left, float top, float right, float bottom) {
        boxModel.setPadding(left, top, right, bottom);
    }

    @Override
    public float getMarginTop() {
        return boxModel.getMarginTop();
    }

    @Override
    public void setMarginTop(float marginTop) {
        boxModel.setMarginTop(marginTop);
    }

    @Override
    public float getMarginBottom() {
        return boxModel.getMarginBottom();
    }

    @Override
    public float getPaddingLeft() {
        return boxModel.getPaddingLeft();
    }

    @Override
    public void setPaddingLeft(float paddingLeft) {
        boxModel.setPaddingLeft(paddingLeft);
    }

    @Override
    public float getPaddingTop() {
        return boxModel.getPaddingTop();
    }

    @Override
    public void setPaddingTop(float paddingTop) {
        boxModel.setPaddingTop(paddingTop);
    }

    @Override
    public float getPaddingRight() {
        return boxModel.getPaddingRight();
    }

    @Override
    public void setPaddingRight(float paddingRight) {
        boxModel.setPaddingRight(paddingRight);
    }

    @Override
    public float getPaddingBottom() {
        return boxModel.getPaddingBottom();
    }

    @Override
    public void setPaddingBottom(float paddingBottom) {
        boxModel.setPaddingBottom(paddingBottom);
    }

    @Override
    @SuppressWarnings("SameParameterValue")
    public void setMarginBottom(float marginBottom) {
        boxModel.setMarginBottom(marginBottom);
    }

    @Override
    public float getMarginLeft() {
        return boxModel.getMarginLeft();
    }

    @Override
    public void setMarginLeft(float marginLeft) {
        boxModel.setMarginLeft(marginLeft);
    }

    @Override
    public float getMarginRight() {
        return boxModel.getMarginRight();
    }

    /**
     * Causes the pixel dimensions used for rendering this Widget
     * to be recalculated.  Should be called any time a parameter that factors
     * into this Widget's size or position is altered.
     */
    public synchronized void refreshLayout() {
        if (positionMetrics == null) {
            // make sure positionMetrics have been set.  this method can be
            // automatically called during xml configuration of certain params
            // before the widget is fully configured.
            return;
        }
        float elementWidth = getWidthPix(plotDimensions.paddedRect.width());
        float elementHeight = getHeightPix(plotDimensions.paddedRect.height());
        PointF coords = calculateCoordinates(elementHeight,
                elementWidth, plotDimensions.paddedRect, positionMetrics);

        RectF widgetRect = new RectF(coords.x, coords.y,
                coords.x + elementWidth, coords.y + elementHeight);
        RectF marginatedWidgetRect = getMarginatedRect(widgetRect);
        RectF paddedWidgetRect = getPaddedRect(marginatedWidgetRect);
        widgetDimensions = new DisplayDimensions(widgetRect,
                marginatedWidgetRect, paddedWidgetRect);
    }

    @Override
    public synchronized void layout(final DisplayDimensions plotDimensions) {
        this.plotDimensions = plotDimensions;
        refreshLayout();
    }


    public static PointF calculateCoordinates(float height, float width, RectF viewRect, PositionMetrics metrics) {
        float x = metrics.getXPositionMetric().getPixelValue(viewRect.width()) + viewRect.left;
        float y = metrics.getYPositionMetric().getPixelValue(viewRect.height()) + viewRect.top;
        PointF point = new PointF(x, y);
        return PixelUtils.sub(point, getAnchorOffset(width, height, metrics.getAnchor()));
    }

    public static PointF getAnchorOffset(float width, float height, Anchor anchor) {
        PointF point = new PointF();
        switch (anchor) {
            case LEFT_TOP:
                break;
            case LEFT_MIDDLE:
                point.set(0, height / 2);
                break;
            case LEFT_BOTTOM:
                point.set(0, height);
                break;
            case RIGHT_TOP:
                point.set(width, 0);
                break;
            case RIGHT_BOTTOM:
                point.set(width, height);
                break;
            case RIGHT_MIDDLE:
                point.set(width, height / 2);
                break;
            case TOP_MIDDLE:
                point.set(width / 2, 0);
                break;
            case BOTTOM_MIDDLE:
                point.set(width / 2, height);
                break;
            case CENTER:
                point.set(width / 2, height / 2);
                break;
            default:
                throw new IllegalArgumentException("Unsupported anchor location: " + anchor);
        }
        return point;
    }

    public static PointF getAnchorCoordinates(RectF widgetRect, Anchor anchor) {
        return PixelUtils.add(new PointF(widgetRect.left, widgetRect.top),
                getAnchorOffset(widgetRect.width(), widgetRect.height(), anchor));
    }

    public static PointF getAnchorCoordinates(float x, float y, float width, float height, Anchor anchor) {
        return getAnchorCoordinates(new RectF(x, y, x + width, y + height), anchor);
    }

    private void checkSize(@NonNull RectF widgetRect) {
        if (lastWidgetRect == null || !lastWidgetRect.equals(widgetRect)) {
            onResize(lastWidgetRect, widgetRect);
        }
        lastWidgetRect = widgetRect;
    }

    /**
     * Called whenever the height or width of the Widget's reserved space has changed,
     * immediately before {@link #doOnDraw(Canvas, RectF)}.
     * May be used to efficiently carry out expensive operations only when necessary.
     *
     * @param oldRect
     * @param newRect
     */
    protected void onResize(@Nullable RectF oldRect, @NonNull RectF newRect) {
        // do nothing by default
    }

    public void draw(Canvas canvas) throws PlotRenderException {
        if (isVisible()) {
            if (backgroundPaint != null) {
                drawBackground(canvas, widgetDimensions.canvasRect);
            }
            canvas.save();
            final RectF widgetRect = applyRotation(canvas, widgetDimensions.paddedRect);
            checkSize(widgetRect);
            doOnDraw(canvas, widgetRect);
            canvas.restore();

            if (borderPaint != null) {
                drawBorder(canvas, widgetRect);
            }
        }
    }

    protected RectF applyRotation(Canvas canvas, RectF rect) {
        float rotationDegs = 0;
        final float cx = widgetDimensions.paddedRect.centerX();
        final float cy = widgetDimensions.paddedRect.centerY();
        final float halfHeight = widgetDimensions.paddedRect.height() / 2;
        final float halfWidth = widgetDimensions.paddedRect.width() / 2;
        switch (rotation) {
            case NINETY_DEGREES:
                rotationDegs = 90;
                rect = new RectF(
                        cx - halfHeight,
                        cy - halfWidth,
                        cx + halfHeight,
                        cy + halfWidth);
                break;
            case NEGATIVE_NINETY_DEGREES:
                rotationDegs = -90;
                rect = new RectF(
                        cx - halfHeight,
                        cy - halfWidth,
                        cx + halfHeight,
                        cy + halfWidth);
                break;
            case ONE_HUNDRED_EIGHTY_DEGREES:
                rotationDegs = 180;
                // fall through
            case NONE:
                break;
            default:
                throw new UnsupportedOperationException("Not yet implemented.");

        }
        if (rotation != Rotation.NONE) {
            canvas.rotate(rotationDegs, cx, cy);
        }
        return rect;
    }

    protected void drawBorder(Canvas canvas, RectF paddedRect) {
        canvas.drawRect(paddedRect, borderPaint);
    }

    protected void drawBackground(Canvas canvas, RectF widgetRect) {
        canvas.drawRect(widgetRect, backgroundPaint);
    }

    /**
     * @param canvas     The Canvas to draw onto
     * @param widgetRect the size and coordinates of this widget
     */
    protected abstract void doOnDraw(Canvas canvas, RectF widgetRect) throws PlotRenderException;

    public Paint getBorderPaint() {
        return borderPaint;
    }

    public void setBorderPaint(Paint borderPaint) {
        this.borderPaint = borderPaint;
    }

    public Paint getBackgroundPaint() {
        return backgroundPaint;
    }

    public void setBackgroundPaint(Paint backgroundPaint) {
        this.backgroundPaint = backgroundPaint;
    }

    public boolean isClippingEnabled() {
        return clippingEnabled;
    }

    public void setClippingEnabled(boolean clippingEnabled) {
        this.clippingEnabled = clippingEnabled;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    public PositionMetrics getPositionMetrics() {
        return positionMetrics;
    }

    public void setPositionMetrics(PositionMetrics positionMetrics) {
        this.positionMetrics = positionMetrics;
    }

    public Rotation getRotation() {
        return rotation;
    }

    public void setRotation(Rotation rotation) {
        this.rotation = rotation;
    }
}
