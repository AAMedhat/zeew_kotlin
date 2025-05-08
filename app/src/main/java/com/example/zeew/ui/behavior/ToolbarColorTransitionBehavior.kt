package com.example.zeew.ui.behavior

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.content.ContextCompat
import com.google.android.material.appbar.AppBarLayout
import kotlin.math.abs

class ToolbarColorTransitionBehavior(context: Context, attrs: AttributeSet?) : CoordinatorLayout.Behavior<Toolbar>(context, attrs) {

    override fun layoutDependsOn(parent: CoordinatorLayout, child: Toolbar, dependency: View): Boolean {
        return dependency is AppBarLayout
    }

    override fun onDependentViewChanged(parent: CoordinatorLayout, child: Toolbar, dependency: View): Boolean {
        if (dependency is AppBarLayout) {
            val maxScroll = dependency.totalScrollRange
            val percentage = abs(dependency.y) / maxScroll

            // Update toolbar background alpha
            child.background?.alpha = (percentage * 255).toInt()

            // Update text and icons color
            updateColors(child, percentage)
        }
        return true
    }

    private fun updateColors(toolbar: Toolbar, percentage: Float) {
        // Find all the ImageViews and TextViews in the toolbar
        toolbar.findViewById<View>(android.R.id.title)?.let { title ->
            // Interpolate between white and black for text
            val textColor = interpolateColor(0xFFFFFFFF.toInt(), 0xFF000000.toInt(), percentage)
            title.setBackgroundColor(textColor)
        }

        // Update all ImageView tints
        val containerLayout = toolbar.getChildAt(0) as? android.widget.LinearLayout
        containerLayout?.let { layout ->
            for (i in 0 until layout.childCount) {
                val child = layout.getChildAt(i)
                if (child is android.widget.ImageView) {
                    val tintColor = interpolateColor(0xFFFFFFFF.toInt(), 0xFF000000.toInt(), percentage)
                    child.setColorFilter(tintColor)
                }
            }
        }
    }

    private fun interpolateColor(startColor: Int, endColor: Int, fraction: Float): Int {
        val startA = (startColor shr 24 and 0xff)
        val startR = (startColor shr 16 and 0xff)
        val startG = (startColor shr 8 and 0xff)
        val startB = (startColor and 0xff)

        val endA = (endColor shr 24 and 0xff)
        val endR = (endColor shr 16 and 0xff)
        val endG = (endColor shr 8 and 0xff)
        val endB = (endColor and 0xff)

        return (startA + (endA - startA) * fraction).toInt() shl 24 or
                (startR + (endR - startR) * fraction).toInt() shl 16 or
                (startG + (endG - startG) * fraction).toInt() shl 8 or
                (startB + (endB - startB) * fraction).toInt()
    }
} 