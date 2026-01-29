package com.example.mangary3.presentation.theme.common

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun SpinningRingIndicator(
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color.LightGray,
    arcColor: Color = Color.Black,
    arcLength: Float = 90f, // góc của phần màu đen chạy (cung)
    strokeWidth: Dp = 6.dp
) {
    val stroke = with(LocalDensity.current) { strokeWidth.toPx() }

    // Infinite transition to animate the start angle
    val transition = rememberInfiniteTransition()
    val startAngle by transition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1200, easing = LinearEasing)
        )
    )

    Canvas(modifier = modifier) {
        val radius = size.minDimension / 2
        val center = Offset(size.width / 2f, size.height / 2f)

        // 1. Draw background ring
        drawCircle(
            color = backgroundColor,
            radius = radius - stroke / 2,
            style = Stroke(width = stroke)
        )

        // 2. Draw animated arc
        drawArc(
            color = arcColor,
            startAngle = startAngle,
            sweepAngle = arcLength,
            useCenter = false,
            style = Stroke(width = stroke, cap = StrokeCap.Round),
            size = Size((radius - stroke / 2) * 2, (radius - stroke / 2) * 2),
            topLeft = Offset(
                (size.width - (radius - stroke / 2) * 2) / 2,
                (size.height - (radius - stroke / 2) * 2) / 2
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SpinningRingIndicatorPreview() {
    SpinningRingIndicator(
        modifier = Modifier.size(200.dp)
    )
}