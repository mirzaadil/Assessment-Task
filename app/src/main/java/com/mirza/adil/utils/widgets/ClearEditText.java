package com.mirza.adil.utils.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.Editable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;

import androidx.appcompat.widget.AppCompatEditText;

import com.mirza.adil.R;


/**
 * The class ClearEditText
 *
 * @author Mirza Adil
 * @version 1.0
 * @email mirza.madil@gmail.com
 * @since 14 Jul 2021
 * <p>
 * This class is used to resolve all the hard android framework dependent objects
 */


public class ClearEditText extends AppCompatEditText {
    public static final int GRAVITY_TOP = 0;
    public static final int GRAVITY_CENTER = 1;
    public static final int GRAVITY_BOTTOM = 2;
    private int mTouchSlop;
    private Drawable mClearDrawable;
    private int mGravity = GRAVITY_CENTER;
    private int mRealPaddingRight;
    private int mDrawablePadding = 0;
    private float[] mDownPoint = new float[2];
    private OnClearClickListener mClearClickListener;

    public ClearEditText(Context context) {
        super(context);
        init(context, null, 0);
    }

    public ClearEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public ClearEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        ViewConfiguration viewConfiguration = ViewConfiguration.get(getContext());
        mTouchSlop = viewConfiguration.getScaledTouchSlop();
        if (attrs != null) {
            TypedArray ta =
                    context.obtainStyledAttributes(
                            attrs, R.styleable.ClearEditText, defStyleAttr, 0);
            try {
                mClearDrawable = ta.getDrawable(R.styleable.ClearEditText_fet_clearDrawable);
                mGravity = ta.getInt(R.styleable.ClearEditText_fet_drawableGravity, GRAVITY_CENTER);
                mDrawablePadding =
                        ta.getDimensionPixelSize(R.styleable.ClearEditText_fet_drawablePadding, 0);
            } finally {
                ta.recycle();
            }
        }
        setPadding(getPaddingLeft(), getPaddingTop(), getPaddingRight(), getPaddingBottom());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            if (getLayoutDirection() == LAYOUT_DIRECTION_RTL) {
                throw new UnsupportedOperationException(
                        "We can not support this feature when the layout is right-to-left");
            }
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        resetClearDrawableBound();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (mClearDrawable != null) {
            int width = mClearDrawable.getIntrinsicWidth() + mDrawablePadding * 2;
            int height = mClearDrawable.getIntrinsicHeight() + mDrawablePadding * 2;
            int measuredWidth = getMeasuredWidth();
            int measuredHeight = getMeasuredHeight();
            int remeasuredWidth = measuredWidth, remeasuredHeight = measuredHeight;
            if (measuredWidth < width) {
                int specMode = MeasureSpec.getMode(widthMeasureSpec);
                int specSize = MeasureSpec.getSize(widthMeasureSpec);
                if (specMode != MeasureSpec.EXACTLY) {
                    remeasuredWidth = Math.max(width, measuredWidth);
                    if (specMode == MeasureSpec.AT_MOST) {
                        remeasuredWidth = Math.min(remeasuredWidth, specSize);
                    }
                }
            }
            if (measuredHeight < height) {
                int specMode = MeasureSpec.getMode(heightMeasureSpec);
                int specSize = MeasureSpec.getSize(heightMeasureSpec);
                if (specMode != MeasureSpec.EXACTLY) {
                    remeasuredHeight = Math.max(height, measuredHeight);
                    if (specMode == MeasureSpec.AT_MOST) {
                        remeasuredHeight = Math.min(remeasuredHeight, specSize);
                    }
                }
            }
            if (remeasuredWidth != measuredWidth || remeasuredHeight != measuredHeight) {
                setMeasuredDimension(remeasuredWidth, remeasuredHeight);
            }
        }
    }

    @Override
    public void setPadding(int left, int top, int right, int bottom) {
        mRealPaddingRight = right;
        if (mClearDrawable != null) {
            right += mClearDrawable.getIntrinsicWidth() + mDrawablePadding * 2;
        }
        super.setPadding(left, top, right, bottom);
        resetClearDrawableBound();
    }

    @Override
    public void setPaddingRelative(int start, int top, int end, int bottom) {
        mRealPaddingRight = end;
        if (mClearDrawable != null) {
            end += mClearDrawable.getIntrinsicWidth() + mDrawablePadding * 2;
        }
        super.setPaddingRelative(start, top, end, bottom);
        resetClearDrawableBound();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mClearDrawable != null && isFocused() && length() > 0) {
            canvas.save();
            canvas.translate(getScrollX(), getScrollY());
            mClearDrawable.draw(canvas);
            canvas.restore();
        }
    }

    @Override
    protected void drawableStateChanged() {
        if (mClearDrawable != null) {
            final int[] state = getDrawableState();
            if (mClearDrawable.isStateful() && mClearDrawable.setState(state)) {
                final Rect dirty = mClearDrawable.getBounds();
                final int scrollX = getScrollX();
                final int scrollY = getScrollY();
                invalidate(
                        dirty.left + scrollX,
                        dirty.top + scrollY,
                        dirty.right + scrollX,
                        dirty.bottom + scrollY);
            }
        }
        super.drawableStateChanged();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (mClearDrawable != null) {
            final float x = event.getX();
            final float y = event.getY();
            final int action = event.getActionMasked();
            switch (action) {
                case MotionEvent.ACTION_DOWN:
                    mDownPoint[0] = x;
                    mDownPoint[1] = y;
                    break;
                case MotionEvent.ACTION_UP:
                    final Rect rect = mClearDrawable.getBounds();
                    if (rect.top - mDrawablePadding <= y
                            && rect.bottom + mDrawablePadding >= y
                            && rect.left - mDrawablePadding <= x
                            && rect.right + mDrawablePadding >= x) {
                        if (Math.abs(mDownPoint[0] - x) <= mTouchSlop
                                && Math.abs(mDownPoint[1] - y) <= mTouchSlop) {
                            if (mClearClickListener != null) {
                                if (!mClearClickListener.onClearClick(this, mClearDrawable)) {
                                    clearTextInTouch(event);
                                }
                            } else {
                                clearTextInTouch(event);
                            }
                            super.onTouchEvent(event);
                            return true;
                        }
                    }
                    break;
            }
        }
        return super.onTouchEvent(event);
    }

    public void setClearDrawable(Drawable drawable) {
        if (mClearDrawable != drawable) {
            mClearDrawable = drawable;
            requestLayout();
        }
    }

    public void setClearDrawablePadding(int pad) {
        if (mDrawablePadding != pad) {
            mDrawablePadding = pad;
            if (mClearDrawable != null) {
                requestLayout();
            }
        }
    }

    public void setOnClearClickListener(OnClearClickListener clickListener) {
        mClearClickListener = clickListener;
    }

    private void clearTextInTouch(MotionEvent event) {
        event.setAction(MotionEvent.ACTION_CANCEL);
        Editable editable = getText();
        if (editable != null) {
            editable.clear();
        }
    }

    private void resetClearDrawableBound() {
        if (mClearDrawable != null) {
            final int top = getPaddingTop() + mDrawablePadding;
            final int bottom = getPaddingBottom() + mDrawablePadding;
            int width = mClearDrawable.getIntrinsicWidth();
            int height = mClearDrawable.getIntrinsicHeight();
            final int newRight = getWidth() - mRealPaddingRight - mDrawablePadding;
            final int h = getHeight();
            switch (mGravity) {
                case GRAVITY_TOP:
                    mClearDrawable.setBounds(newRight - width, top, newRight, top + height);
                    break;
                case GRAVITY_CENTER:
                    int newTop = top + (h - top - bottom - height) / 2;
                    mClearDrawable.setBounds(newRight - width, newTop, newRight, newTop + height);
                    break;
                case GRAVITY_BOTTOM:
                default:
                    int newBottom = h - bottom;
                    mClearDrawable.setBounds(
                            newRight - width, newBottom - height, newRight, newBottom);
                    break;
            }
        }
    }

    public interface OnClearClickListener {
        boolean onClearClick(ClearEditText editText, Drawable drawable);
    }
}
