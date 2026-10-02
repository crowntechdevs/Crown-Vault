package com.example.ui.components

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.CompareArrows
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.model.*
import com.example.ui.theme.CrownColors
import com.example.viewmodel.CrownVaultUiState

/**
 * Brand logo component backed by the local drawable resource `R.drawable.crown_vault_logo`.
 * Preserves 1:1 aspect ratio with `ContentScale.Fit`, adapts cleanly across dark and light modes,
 * and supports optional gentle breathing motion for the main header mark.
 */
@Composable
fun CrownBrandIcon(
    modifier: Modifier = Modifier,
    size: Dp = 22.dp,
    tint: Color = Color.Unspecified,
    animated: Boolean = false
) {
    val isLight = CrownColors.isLight
    val cornerRadius = (size.value * 0.28f).dp

    val scaleAnim: Float
    val rotAnim: Float
    if (animated) {
        val infiniteTransition = rememberInfiniteTransition(label = "logoBreathe")
        val s by infiniteTransition.animateFloat(
            initialValue = 1f,
            targetValue = 1.06f,
            animationSpec = infiniteRepeatable(
                animation = tween(3500, easing = FastOutSlowInEasing),
                repeatMode = RepeatMode.Reverse
            ),
            label = "logoScale"
        )
        val r by infiniteTransition.animateFloat(
            initialValue = -4f,
            targetValue = 4f,
            animationSpec = infiniteRepeatable(
                animation = tween(3500, easing = FastOutSlowInEasing),
                repeatMode = RepeatMode.Reverse
            ),
            label = "logoRot"
        )
        scaleAnim = s
        rotAnim = r
    } else {
        scaleAnim = 1f
        rotAnim = 0f
    }

    Box(
        modifier = modifier
            .size(size)
            .aspectRatio(1f)
            .scale(scaleAnim)
            .rotate(rotAnim)
            .shadow(
                elevation = if (isLight) 2.dp else 4.dp,
                shape = RoundedCornerShape(cornerRadius),
                ambientColor = Color(0xFF56F2DF),
                spotColor = Color(0xFF68B3FF)
            )
            .clip(RoundedCornerShape(cornerRadius))
            .background(Color(0xFF0D0E20))
            .border(
                width = 0.8.dp,
                color = if (isLight) Color(0x4D009B8D) else Color(0x4D5EF0E3),
                shape = RoundedCornerShape(cornerRadius)
            ),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.crown_vault_logo),
            contentDescription = stringResource(R.string.content_desc_brand_logo),
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .fillMaxSize()
                .aspectRatio(1f)
        )
    }
}

@Composable
fun SolanaVectorIcon(size: Dp = 18.dp) {
    Canvas(modifier = Modifier.size(size)) {
        val w = this.size.width
        val h = this.size.height

        fun barPath(topY: Float, bottomY: Float, slantRight: Boolean): Path {
            return Path().apply {
                if (slantRight) {
                    moveTo(w * 0.22f, bottomY)
                    lineTo(w * 0.35f, topY)
                    lineTo(w * 0.82f, topY)
                    lineTo(w * 0.69f, bottomY)
                    close()
                } else {
                    moveTo(w * 0.18f, bottomY)
                    lineTo(w * 0.65f, bottomY)
                    lineTo(w * 0.78f, topY)
                    lineTo(w * 0.31f, topY)
                    close()
                }
            }
        }

        drawPath(barPath(h * 0.21f, h * 0.38f, true), color = CrownColors.SolPurple)
        drawPath(barPath(h * 0.43f, h * 0.60f, false), color = CrownColors.SolPurple)
        drawPath(barPath(h * 0.64f, h * 0.81f, true), color = CrownColors.SolGreen)
    }
}

@Composable
fun UsdtVectorIcon(size: Dp = 18.dp) {
    Canvas(modifier = Modifier.size(size)) {
        val w = this.size.width
        val h = this.size.height
        drawCircle(color = CrownColors.UsdtTeal, radius = w * 0.46f)
        drawRect(
            color = Color.White,
            topLeft = Offset(w * 0.30f, h * 0.26f),
            size = Size(w * 0.40f, h * 0.11f)
        )
        drawRect(
            color = Color.White,
            topLeft = Offset(w * 0.44f, h * 0.26f),
            size = Size(w * 0.12f, h * 0.48f)
        )
        drawOval(
            color = Color.White,
            topLeft = Offset(w * 0.26f, h * 0.43f),
            size = Size(w * 0.48f, h * 0.11f),
            style = Stroke(width = w * 0.055f)
        )
    }
}

@Composable
fun EthVectorIcon(size: Dp = 18.dp) {
    Canvas(modifier = Modifier.size(size)) {
        val w = this.size.width
        val h = this.size.height
        drawCircle(color = CrownColors.EthBlue, radius = w * 0.46f)

        val topLeft = Path().apply {
            moveTo(w * 0.50f, h * 0.19f)
            lineTo(w * 0.31f, h * 0.51f)
            lineTo(w * 0.50f, h * 0.42f)
            close()
        }
        val topRight = Path().apply {
            moveTo(w * 0.50f, h * 0.19f)
            lineTo(w * 0.50f, h * 0.42f)
            lineTo(w * 0.69f, h * 0.51f)
            close()
        }
        val bottomLeft = Path().apply {
            moveTo(w * 0.50f, h * 0.81f)
            lineTo(w * 0.50f, h * 0.66f)
            lineTo(w * 0.31f, h * 0.55f)
            close()
        }
        val bottomRight = Path().apply {
            moveTo(w * 0.50f, h * 0.81f)
            lineTo(w * 0.69f, h * 0.55f)
            lineTo(w * 0.50f, h * 0.66f)
            close()
        }
        drawPath(topLeft, Color.White)
        drawPath(topRight, Color.White.copy(alpha = 0.65f))
        drawPath(bottomLeft, Color.White)
        drawPath(bottomRight, Color.White.copy(alpha = 0.65f))
    }
}

@Composable
fun BtcVectorIcon(size: Dp = 18.dp) {
    Box(
        modifier = Modifier.size(size),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "₿",
            color = CrownColors.WarmAmber,
            fontSize = (size.value * 0.82f).sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Monospace
        )
    }
}

@Composable
fun SphereVectorIcon(size: Dp = 18.dp) {
    Canvas(modifier = Modifier.size(size)) {
        val w = this.size.width
        val h = this.size.height
        val center = Offset(w * 0.5f, h * 0.5f)
        drawCircle(
            brush = Brush.radialGradient(
                colors = listOf(Color(0xFF56F2DF), Color(0xFF9A7BFF), Color(0xFF3E2A8A)),
                center = Offset(w * 0.38f, h * 0.35f),
                radius = w * 0.55f
            ),
            radius = w * 0.44f,
            center = center
        )
        drawOval(
            color = Color.White.copy(alpha = 0.75f),
            topLeft = Offset(w * 0.14f, h * 0.36f),
            size = Size(w * 0.72f, h * 0.28f),
            style = Stroke(width = w * 0.065f)
        )
        drawOval(
            color = Color(0xFF56F2DF).copy(alpha = 0.85f),
            topLeft = Offset(w * 0.36f, h * 0.14f),
            size = Size(w * 0.28f, h * 0.72f),
            style = Stroke(width = w * 0.055f)
        )
    }
}

@Composable
fun AssetIconBadge(
    asset: Asset,
    boxSize: Dp = 35.dp,
    iconSize: Dp = 18.dp
) {
    val bgColor = when (asset.icon) {
        AssetIconType.CROWN -> Color(0x1F46E1CC)
        AssetIconType.BTC -> Color(0x1FFFA44D)
        AssetIconType.ETH -> Color(0x21A278FF)
        AssetIconType.SOL -> Color(0x1F9945FF)
        AssetIconType.SPHR -> Color(0x248E6BFF)
        AssetIconType.USDT -> Color(0x1F26A17B)
    }
    Box(
        modifier = Modifier
            .size(boxSize)
            .clip(RoundedCornerShape(11.dp))
            .background(bgColor),
        contentAlignment = Alignment.Center
    ) {
        when (asset.icon) {
            AssetIconType.CROWN -> CrownBrandIcon(size = (boxSize.value * 0.88f).dp)
            AssetIconType.BTC -> BtcVectorIcon(size = iconSize)
            AssetIconType.ETH -> EthVectorIcon(size = iconSize)
            AssetIconType.SOL -> SolanaVectorIcon(size = iconSize)
            AssetIconType.SPHR -> SphereVectorIcon(size = iconSize)
            AssetIconType.USDT -> UsdtVectorIcon(size = iconSize)
        }
    }
}

@Composable
fun AssetMiniSparkline(
    asset: Asset,
    modifier: Modifier = Modifier
) {
    val points = remember(asset.ticker) { asset.priceSeriesFor("1D") }
    val strokeColor = when (asset.tone) {
        "orange" -> Color(0xFFFFA84C)
        "purple" -> Color(0xFFB388FF)
        "green" -> Color(0xFF4BE3B5)
        "teal" -> Color(0xFF46D6B8)
        else -> Color(0xFF55F4DC)
    }
    Canvas(modifier = modifier) {
        if (points.size < 2) return@Canvas
        val w = size.width
        val h = size.height
        val minVal = points.minOrNull() ?: 0f
        val maxVal = points.maxOrNull() ?: 1f
        val span = (maxVal - minVal).takeIf { it > 0.00001f } ?: 1f

        val path = Path()
        points.forEachIndexed { idx, value ->
            val x = (idx.toFloat() / (points.size - 1)) * w
            val norm = (value - minVal) / span
            val y = h * 0.82f - norm * (h * 0.68f)
            if (idx == 0) {
                path.moveTo(x, y)
            } else {
                val prevX = ((idx - 1).toFloat() / (points.size - 1)) * w
                val prevNorm = (points[idx - 1] - minVal) / span
                val prevY = h * 0.82f - prevNorm * (h * 0.68f)
                val ctrlX = (prevX + x) * 0.5f
                path.cubicTo(ctrlX, prevY, ctrlX, y, x, y)
            }
        }
        drawPath(
            path = path,
            color = strokeColor,
            style = Stroke(width = 1.8.dp.toPx(), cap = StrokeCap.Round)
        )
    }
}

@Composable
fun VaultBalanceChart(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier) {
        val w = size.width
        val h = size.height
        fun sx(x: Float) = (x / 360f) * w
        fun sy(y: Float) = (y / 92f) * h

        val linePath = Path().apply {
            moveTo(sx(0f), sy(72f))
            cubicTo(sx(25f), sy(66f), sx(30f), sy(70f), sx(48f), sy(52f))
            cubicTo(sx(66f), sy(34f), sx(78f), sy(70f), sx(93f), sy(58f))
            cubicTo(sx(108f), sy(46f), sx(120f), sy(45f), sx(132f), sy(53f))
            cubicTo(sx(144f), sy(61f), sx(150f), sy(35f), sx(166f), sy(43f))
            cubicTo(sx(182f), sy(51f), sx(190f), sy(23f), sx(202f), sy(35f))
            cubicTo(sx(214f), sy(47f), sx(230f), sy(24f), sx(244f), sy(29f))
            cubicTo(sx(258f), sy(34f), sx(263f), sy(52f), sx(278f), sy(36f))
            cubicTo(sx(293f), sy(20f), sx(310f), sy(31f), sx(328f), sy(15f))
            cubicTo(sx(346f), sy(2f), sx(349f), sy(18f), sx(360f), sy(7f))
        }

        val fillPath = Path().apply {
            addPath(linePath)
            lineTo(w, h)
            lineTo(0f, h)
            close()
        }

        drawPath(
            path = fillPath,
            brush = Brush.verticalGradient(
                colors = listOf(
                    CrownColors.DeepViolet.copy(alpha = 0.34f),
                    CrownColors.DeepViolet.copy(alpha = 0f)
                )
            )
        )

        drawPath(
            path = linePath,
            brush = Brush.horizontalGradient(
                colors = listOf(
                    Color(0xFF27E8E8),
                    Color(0xFFB371FF),
                    Color(0xFFFFB46B)
                )
            ),
            style = Stroke(width = 2.6.dp.toPx(), cap = StrokeCap.Round)
        )
    }
}

@Composable
fun MarketSparkline(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier) {
        val w = size.width
        val h = size.height
        fun sx(x: Float) = (x / 120f) * w
        fun sy(y: Float) = (y / 54f) * h

        val path = Path().apply {
            moveTo(sx(2f), sy(43f))
            cubicTo(sx(16f), sy(40f), sx(17f), sy(17f), sx(29f), sy(28f))
            cubicTo(sx(41f), sy(39f), sx(45f), sy(22f), sx(53f), sy(27f))
            cubicTo(sx(61f), sy(32f), sx(68f), sy(8f), sx(76f), sy(17f))
            cubicTo(sx(84f), sy(26f), sx(88f), sy(31f), sx(97f), sy(15f))
            cubicTo(sx(106f), sy(2f), sx(107f), sy(11f), sx(118f), sy(3f))
        }

        drawPath(
            path = path,
            color = Color(0xFF55F4DC),
            style = Stroke(width = 2.5.dp.toPx(), cap = StrokeCap.Round)
        )
    }
}

@Composable
fun InteractiveTokenPriceChart(
    asset: Asset,
    timeframe: String,
    chartStyle: String,
    onInspectPoint: (priceText: String?, timeLabel: String?, deltaText: String?) -> Unit = { _, _, _ -> },
    modifier: Modifier = Modifier
) {
    val points = remember(asset.ticker, timeframe) { asset.priceSeriesFor(timeframe) }
    val candles = remember(asset.ticker, timeframe) { asset.candlesFor(timeframe) }
    val timeLabels = remember(asset.ticker, timeframe) { Asset.timeLabelsFor(timeframe, points.size) }

    var selectedIndex by remember(asset.ticker, timeframe, chartStyle) { mutableStateOf<Int?>(null) }

    val animProgress = remember(asset.ticker, timeframe, chartStyle) { Animatable(0f) }
    LaunchedEffect(asset.ticker, timeframe, chartStyle) {
        selectedIndex = null
        onInspectPoint(null, null, null)
        animProgress.snapTo(0.15f)
        animProgress.animateTo(
            targetValue = 1f,
            animationSpec = tween(durationMillis = 480, easing = FastOutSlowInEasing)
        )
    }

    val primaryAccent = when (asset.tone) {
        "orange" -> Color(0xFFFFA84C)
        "purple" -> Color(0xFFB388FF)
        "green" -> Color(0xFF39E8A8)
        "teal" -> Color(0xFF42DFC8)
        else -> Color(0xFF55F4DC)
    }

    val minVal = remember(points, candles, chartStyle) {
        if (chartStyle == "candles") candles.minOfOrNull { it.low } ?: 0f
        else points.minOrNull() ?: 0f
    }
    val maxVal = remember(points, candles, chartStyle) {
        if (chartStyle == "candles") candles.maxOfOrNull { it.high } ?: 1f
        else points.maxOrNull() ?: 1f
    }
    val midVal = (minVal + maxVal) * 0.5f

    fun updateSelectionFromX(xPx: Float, widthPx: Float) {
        if (widthPx <= 0f || points.isEmpty()) return
        val ratio = (xPx / widthPx).coerceIn(0f, 1f)
        val idx = (ratio * (points.size - 1)).toInt().coerceIn(0, points.size - 1)
        selectedIndex = idx
        val firstPrice = points.firstOrNull() ?: 1f
        val currentPrice = points[idx]
        val pct = if (firstPrice > 0f) ((currentPrice - firstPrice) / firstPrice) * 100f else 0f
        val sign = if (pct >= 0f) "+" else ""
        val deltaFormatted = "$sign${"%.2f".format(java.util.Locale.US, pct)}%"
        onInspectPoint(
            asset.formatPrice(currentPrice),
            timeLabels.getOrElse(idx) { "" },
            deltaFormatted
        )
    }

    Column(modifier = modifier.fillMaxWidth()) {
        // Interactive Chart Surface
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(168.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(Color.Black.copy(alpha = 0.22f))
                .border(1.dp, CrownColors.SurfaceBorder, RoundedCornerShape(12.dp))
                .padding(horizontal = 10.dp, vertical = 10.dp)
                .testTag("interactive_price_chart")
        ) {
            Canvas(
                modifier = Modifier
                    .fillMaxSize()
                    .pointerInput(asset.ticker, timeframe, chartStyle) {
                        detectTapGestures(
                            onPress = { offset ->
                                updateSelectionFromX(offset.x, size.width.toFloat())
                                tryAwaitRelease()
                            }
                        )
                    }
                    .pointerInput(asset.ticker, timeframe, chartStyle) {
                        detectHorizontalDragGestures(
                            onDragStart = { offset ->
                                updateSelectionFromX(offset.x, size.width.toFloat())
                            },
                            onHorizontalDrag = { change, _ ->
                                change.consume()
                                updateSelectionFromX(change.position.x, size.width.toFloat())
                            },
                            onDragEnd = {},
                            onDragCancel = {}
                        )
                    }
            ) {
                val w = size.width
                val h = size.height
                val chartTop = h * 0.08f
                val chartBottom = h * 0.76f
                val chartHeight = chartBottom - chartTop
                val volTop = h * 0.80f
                val volBottom = h * 0.98f
                val volHeight = volBottom - volTop
                val span = (maxVal - minVal).takeIf { it > 0.000001f } ?: 1f

                fun priceToY(price: Float): Float {
                    val normalized = ((price - minVal) / span).coerceIn(0f, 1f)
                    val animatedNorm = 0.5f + (normalized - 0.5f) * animProgress.value
                    return chartBottom - animatedNorm * chartHeight
                }

                // Horizontal reference grid lines (TradingView style)
                val dashEffect = PathEffect.dashPathEffect(floatArrayOf(6f, 6f), 0f)
                listOf(0.12f, 0.44f, 0.76f).forEach { frac ->
                    val yLine = h * frac
                    drawLine(
                        color = Color.White.copy(alpha = 0.07f),
                        start = Offset(0f, yLine),
                        end = Offset(w, yLine),
                        strokeWidth = 1f,
                        pathEffect = dashEffect
                    )
                }

                // Volume histogram bars at the bottom
                val barSlotWidth = w / candles.size.coerceAtLeast(1)
                candles.forEachIndexed { idx, candle ->
                    val xCenter = if (candles.size > 1) {
                        (idx.toFloat() / (candles.size - 1)) * (w - barSlotWidth * 0.6f) + barSlotWidth * 0.3f
                    } else w * 0.5f
                    val isBull = candle.close >= candle.open
                    val barH = candle.volume * volHeight * animProgress.value
                    drawRoundRect(
                        color = if (isBull) primaryAccent.copy(alpha = 0.24f) else Color(0xFFFF6B6B).copy(alpha = 0.24f),
                        topLeft = Offset(xCenter - barSlotWidth * 0.22f, volBottom - barH),
                        size = Size(barSlotWidth * 0.44f, barH),
                        cornerRadius = CornerRadius(2.dp.toPx(), 2.dp.toPx())
                    )
                }

                if (chartStyle == "candles") {
                    // TradingView Candlestick mode
                    candles.forEachIndexed { idx, candle ->
                        val x = if (candles.size > 1) {
                            (idx.toFloat() / (candles.size - 1)) * (w - barSlotWidth * 0.6f) + barSlotWidth * 0.3f
                        } else w * 0.5f
                        val isBull = candle.close >= candle.open
                        val candleColor = if (isBull) Color(0xFF4BE3B5) else Color(0xFFFF6B6B)
                        val highY = priceToY(candle.high)
                        val lowY = priceToY(candle.low)
                        val openY = priceToY(candle.open)
                        val closeY = priceToY(candle.close)

                        // Wick
                        drawLine(
                            color = candleColor,
                            start = Offset(x, highY),
                            end = Offset(x, lowY),
                            strokeWidth = 1.5.dp.toPx(),
                            cap = StrokeCap.Round
                        )

                        // Body
                        val bodyTop = minOf(openY, closeY)
                        val bodyBottom = maxOf(openY, closeY)
                        val bodyHeight = (bodyBottom - bodyTop).coerceAtLeast(3.dp.toPx())
                        drawRoundRect(
                            color = candleColor,
                            topLeft = Offset(x - barSlotWidth * 0.26f, bodyTop),
                            size = Size(barSlotWidth * 0.52f, bodyHeight),
                            cornerRadius = CornerRadius(2.dp.toPx(), 2.dp.toPx())
                        )
                    }
                } else {
                    // Recharts Smooth Area / Line mode
                    if (points.size >= 2) {
                        val linePath = Path()
                        points.forEachIndexed { idx, value ->
                            val x = (idx.toFloat() / (points.size - 1)) * w
                            val y = priceToY(value)
                            if (idx == 0) {
                                linePath.moveTo(x, y)
                            } else {
                                val prevX = ((idx - 1).toFloat() / (points.size - 1)) * w
                                val prevY = priceToY(points[idx - 1])
                                val ctrlX = (prevX + x) * 0.5f
                                linePath.cubicTo(ctrlX, prevY, ctrlX, y, x, y)
                            }
                        }

                        val fillPath = Path().apply {
                            addPath(linePath)
                            lineTo(w, chartBottom)
                            lineTo(0f, chartBottom)
                            close()
                        }

                        drawPath(
                            path = fillPath,
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    primaryAccent.copy(alpha = 0.34f),
                                    primaryAccent.copy(alpha = 0.06f),
                                    Color.Transparent
                                ),
                                startY = chartTop,
                                endY = chartBottom
                            )
                        )

                        drawPath(
                            path = linePath,
                            brush = Brush.horizontalGradient(
                                colors = listOf(
                                    primaryAccent,
                                    Color(0xFF68B3FF),
                                    primaryAccent
                                )
                            ),
                            style = Stroke(width = 2.6.dp.toPx(), cap = StrokeCap.Round)
                        )
                    }
                }

                // Active Crosshair & Glowing Point Marker when scrubbing/tapping
                val activeIdx = selectedIndex ?: (points.size - 1).coerceAtLeast(0)
                if (points.isNotEmpty()) {
                    val activeX = if (points.size > 1) {
                        (activeIdx.toFloat() / (points.size - 1)) * w
                    } else w * 0.5f
                    val activeY = priceToY(points[activeIdx])

                    if (selectedIndex != null) {
                        drawLine(
                            color = primaryAccent.copy(alpha = 0.55f),
                            start = Offset(activeX, 0f),
                            end = Offset(activeX, h),
                            strokeWidth = 1.2.dp.toPx(),
                            pathEffect = dashEffect
                        )
                        drawLine(
                            color = primaryAccent.copy(alpha = 0.35f),
                            start = Offset(0f, activeY),
                            end = Offset(w, activeY),
                            strokeWidth = 1.dp.toPx(),
                            pathEffect = dashEffect
                        )
                    }

                    drawCircle(
                        color = primaryAccent.copy(alpha = 0.28f),
                        radius = 8.dp.toPx(),
                        center = Offset(activeX, activeY)
                    )
                    drawCircle(
                        color = primaryAccent,
                        radius = 4.dp.toPx(),
                        center = Offset(activeX, activeY)
                    )
                    drawCircle(
                        color = Color(0xFF0A0C18),
                        radius = 1.8.dp.toPx(),
                        center = Offset(activeX, activeY)
                    )
                }
            }

            // Right-side Y-axis price scale labels
            Column(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .fillMaxHeight(0.76f),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = asset.formatPrice(maxVal),
                    color = CrownColors.TextSubtle,
                    fontSize = 8.sp,
                    fontFamily = FontFamily.Monospace
                )
                Text(
                    text = asset.formatPrice(midVal),
                    color = CrownColors.TextSubtle,
                    fontSize = 8.sp,
                    fontFamily = FontFamily.Monospace
                )
                Text(
                    text = asset.formatPrice(minVal),
                    color = CrownColors.TextSubtle,
                    fontSize = 8.sp,
                    fontFamily = FontFamily.Monospace
                )
            }

            // Top-left interactive crosshair badge when user scrubs/taps
            selectedIndex?.let { idx ->
                val pointPrice = asset.formatPrice(points[idx])
                val pointTime = timeLabels.getOrElse(idx) { "" }
                Row(
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .clip(RoundedCornerShape(6.dp))
                        .background(Color(0xD90E1124))
                        .border(1.dp, primaryAccent.copy(alpha = 0.45f), RoundedCornerShape(6.dp))
                        .padding(horizontal = 8.dp, vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Text(
                        text = pointTime,
                        color = CrownColors.TextMuted,
                        fontSize = 9.sp,
                        fontFamily = FontFamily.Monospace
                    )
                    Text(
                        text = pointPrice,
                        color = primaryAccent,
                        fontSize = 10.sp,
                        fontFamily = FontFamily.Monospace,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        // X-axis Time Labels Row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 6.dp, start = 4.dp, end = 4.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            val indicesToShow = listOf(0, (timeLabels.size / 3), (timeLabels.size * 2 / 3), (timeLabels.size - 1).coerceAtLeast(0)).distinct()
            indicesToShow.forEach { i ->
                Text(
                    text = timeLabels.getOrElse(i) { "" },
                    color = CrownColors.TextSubtle,
                    fontSize = 9.sp,
                    fontFamily = FontFamily.Monospace
                )
            }
        }
    }
}

@Composable
fun AmbientShellBackground(
    themeMode: String,
    modifier: Modifier = Modifier
) {
    val isLight = CrownColors.isLight
    val isNeon = CrownColors.isNeon
    val glowMultiplier = if (isNeon) 1.75f else 1f

    val coreColor by animateColorAsState(
        targetValue = CrownColors.NebulaCore,
        animationSpec = tween(420),
        label = "coreColorAnim"
    )
    val voidColor by animateColorAsState(
        targetValue = CrownColors.DeepVoid,
        animationSpec = tween(420),
        label = "voidColorAnim"
    )
    val violetGlow by animateColorAsState(
        targetValue = CrownColors.AmbientViolet,
        animationSpec = tween(420),
        label = "violetGlowAnim"
    )
    val cyanGlow by animateColorAsState(
        targetValue = CrownColors.AmbientCyan,
        animationSpec = tween(420),
        label = "cyanGlowAnim"
    )

    val infiniteTransition = rememberInfiniteTransition(label = "floatingTorus")
    val floatOffset by infiniteTransition.animateFloat(
        initialValue = -10f,
        targetValue = 12f,
        animationSpec = infiniteRepeatable(
            animation = tween(6000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "floatOffset"
    )

    Box(modifier = modifier.fillMaxSize()) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val w = size.width
            val h = size.height

            drawRect(
                brush = Brush.radialGradient(
                    colors = when {
                        isLight -> listOf(coreColor, Color(0xFFE8F0FC), voidColor)
                        isNeon -> listOf(coreColor, Color(0xFF14072B), voidColor)
                        else -> listOf(coreColor, Color(0xFF080913), voidColor)
                    },
                    center = Offset(w * 0.5f, h * 0.22f),
                    radius = maxOf(w, h) * 0.85f
                )
            )

            drawCircle(
                brush = Brush.radialGradient(
                    colors = listOf(
                        violetGlow.copy(
                            alpha = (if (isLight) 0.14f else 0.22f * glowMultiplier).coerceAtMost(0.52f)
                        ),
                        Color.Transparent
                    ),
                    center = Offset(w * 0.18f, h * 0.15f),
                    radius = w * 0.68f
                ),
                radius = w * 0.68f,
                center = Offset(w * 0.18f, h * 0.15f)
            )

            drawCircle(
                brush = Brush.radialGradient(
                    colors = listOf(
                        cyanGlow.copy(
                            alpha = (if (isLight) 0.12f else 0.16f * glowMultiplier).coerceAtMost(0.46f)
                        ),
                        Color.Transparent
                    ),
                    center = Offset(w * 0.85f, h * 0.82f),
                    radius = w * 0.64f
                ),
                radius = w * 0.64f,
                center = Offset(w * 0.85f, h * 0.82f)
            )

            if (isNeon) {
                drawCircle(
                    brush = Brush.radialGradient(
                        colors = listOf(
                            Color(0xFF00FFF0).copy(alpha = 0.18f),
                            Color.Transparent
                        ),
                        center = Offset(w * 0.82f, h * 0.24f),
                        radius = w * 0.52f
                    ),
                    radius = w * 0.52f,
                    center = Offset(w * 0.82f, h * 0.24f)
                )
            }

            val step = 48.dp.toPx()
            val gridColor = when {
                isLight -> Color(0xFF0E1326).copy(alpha = 0.04f)
                isNeon -> Color(0xFF00FFF0).copy(alpha = 0.055f)
                else -> Color.White.copy(alpha = 0.025f)
            }
            var x = 0f
            while (x < w) {
                drawLine(gridColor, Offset(x, 0f), Offset(x, h), strokeWidth = 1f)
                x += step
            }
            var y = 0f
            while (y < h) {
                drawLine(gridColor, Offset(0f, y), Offset(w, y), strokeWidth = 1f)
                y += step
            }
        }

        // Floating ambient logo watermarks (matching .floating-torus-one and .floating-torus-two)
        Image(
            painter = painterResource(id = R.drawable.crown_vault_logo),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .align(Alignment.TopStart)
                .offset(x = (-68).dp, y = (72 + floatOffset).dp)
                .size(190.dp)
                .aspectRatio(1f)
                .rotate(-22f)
                .clip(RoundedCornerShape(44.dp))
                .alpha(if (isLight) 0.06f else 0.13f)
        )

        Image(
            painter = painterResource(id = R.drawable.crown_vault_logo),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .offset(x = 68.dp, y = (-110 - floatOffset).dp)
                .size(170.dp)
                .aspectRatio(1f)
                .rotate(16f)
                .clip(RoundedCornerShape(40.dp))
                .alpha(if (isLight) 0.05f else 0.10f)
        )
    }
}

@Composable
fun TopBar(
    onOpenDrawer: () -> Unit,
    onOpenNotifications: () -> Unit
) {
    val isLight = CrownColors.isLight
    val buttonSurface = if (isLight) Color(0xFF0E1326).copy(alpha = 0.06f) else Color.White.copy(alpha = 0.05f)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .height(68.dp)
            .padding(horizontal = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(RoundedCornerShape(12.dp))
                .clickable(onClick = onOpenDrawer)
                .testTag("open_drawer_button"),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(RoundedCornerShape(11.dp))
                    .background(buttonSurface),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = stringResource(R.string.content_desc_open_menu),
                    tint = CrownColors.TextSecondary,
                    modifier = Modifier.size(19.dp)
                )
            }
        }

        // Brand mark + typography vertically centered
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(9.dp),
            modifier = Modifier.testTag("header_brand_logo")
        ) {
            CrownBrandIcon(
                size = 30.dp,
                animated = true
            )
            Text(
                text = buildAnnotatedString {
                    withStyle(SpanStyle(color = CrownColors.TextPrimary, fontWeight = FontWeight.Bold)) {
                        append(stringResource(R.string.brand_crown))
                    }
                    withStyle(SpanStyle(color = CrownColors.NeonCyan, fontWeight = FontWeight.Bold)) {
                        append(stringResource(R.string.brand_vault))
                    }
                },
                fontSize = 12.sp,
                letterSpacing = 2.6.sp
            )
        }

        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(RoundedCornerShape(12.dp))
                .clickable(onClick = onOpenNotifications)
                .testTag("notifications_button"),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(RoundedCornerShape(11.dp))
                    .background(buttonSurface),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Outlined.Notifications,
                    contentDescription = stringResource(R.string.content_desc_notifications),
                    tint = CrownColors.TextSecondary,
                    modifier = Modifier.size(18.dp)
                )
                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(top = 7.dp, end = 7.dp)
                        .size(6.dp)
                        .clip(CircleShape)
                        .background(CrownColors.CoralDot)
                )
            }
        }
    }
    HorizontalDivider(color = CrownColors.SurfaceBorder, thickness = 1.dp)
}

@Composable
fun EyebrowNetworkRow(
    t: TranslationSet,
    networkPref: String
) {
    val infiniteTransition = rememberInfiniteTransition(label = "pulse")
    val pulseAlpha by infiniteTransition.animateFloat(
        initialValue = 0.55f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(1100, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "pulseAlpha"
    )

    val networkDisplay = when (networkPref) {
        "quantum" -> t.system.netQuantumMesh
        "direct" -> t.system.netDirectLink
        else -> t.topbar.network
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(7.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(6.dp)
                    .alpha(pulseAlpha)
                    .clip(CircleShape)
                    .background(CrownColors.NeonCyan)
            )
            Text(
                text = networkDisplay,
                color = CrownColors.TextMuted,
                fontSize = 11.sp,
                letterSpacing = 0.5.sp
            )
        }

        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(5.dp))
                .background(Color(0x144AE4D1))
                .border(1.dp, Color(0x2B4AE4D1), RoundedCornerShape(5.dp))
                .padding(horizontal = 7.dp, vertical = 3.dp)
        ) {
            Text(
                text = t.topbar.live,
                color = CrownColors.NeonCyan,
                fontSize = 9.sp,
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.Medium,
                letterSpacing = 0.8.sp
            )
        }
    }
}

private data class NavBarEntry(
    val screen: Screen,
    val labelSelector: (NavStrings) -> String,
    val icon: ImageVector,
    val useBrandLogo: Boolean = false
)

private val bottomNavEntries = listOf(
    NavBarEntry(Screen.VAULT, { it.vault }, Icons.Outlined.AccountBalanceWallet, useBrandLogo = true),
    NavBarEntry(Screen.MARKETS, { it.markets }, Icons.Outlined.BarChart),
    NavBarEntry(Screen.STAKING, { it.staking }, Icons.Outlined.Lock),
    NavBarEntry(Screen.SWAP, { it.swap }, Icons.AutoMirrored.Filled.CompareArrows),
    NavBarEntry(Screen.CARD, { it.card }, Icons.Outlined.CreditCard),
    NavBarEntry(Screen.PLUS, { it.plus }, Icons.Outlined.AutoAwesome),
    NavBarEntry(Screen.ACTIVITY, { _ -> "Activity" }, Icons.Outlined.History)
)

@Composable
fun BottomNavBar(
    currentScreen: Screen,
    t: TranslationSet,
    onSelectScreen: (Screen) -> Unit
) {
    val isLight = CrownColors.isLight
    val isNeon = CrownColors.isNeon
    Surface(
        color = when {
            isLight -> Color(0xF5FFFFFF)
            isNeon -> Color(0xEE110626)
            else -> Color(0xE8090A15)
        },
        tonalElevation = 8.dp,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.navigationBarsPadding()) {
            HorizontalDivider(color = CrownColors.SurfaceBorder, thickness = 1.dp)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(68.dp)
                    .padding(horizontal = 4.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                bottomNavEntries.forEach { entry ->
                    val isSelected = currentScreen == entry.screen
                    val tint = if (isSelected) CrownColors.NeonCyan else CrownColors.TextMuted
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                            .clip(RoundedCornerShape(10.dp))
                            .clickable { onSelectScreen(entry.screen) }
                            .testTag("nav_${entry.screen.route}"),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        if (entry.useBrandLogo) {
                            CrownBrandIcon(
                                size = 20.dp,
                                modifier = Modifier.alpha(if (isSelected) 1f else 0.72f)
                            )
                        } else {
                            Icon(
                                imageVector = entry.icon,
                                contentDescription = entry.labelSelector(t.nav),
                                tint = tint,
                                modifier = Modifier.size(19.dp)
                            )
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = entry.labelSelector(t.nav),
                            color = tint,
                            fontSize = 9.sp,
                            fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ToastOverlay(
    message: String,
    modifier: Modifier = Modifier
) {
    AnimatedVisibility(
        visible = message.isNotEmpty(),
        enter = fadeIn() + slideInVertically(initialOffsetY = { -it / 2 }),
        exit = fadeOut() + slideOutVertically(targetOffsetY = { -it / 2 }),
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .statusBarsPadding()
                .padding(top = 14.dp, start = 16.dp, end = 16.dp)
                .shadow(16.dp, RoundedCornerShape(10.dp))
                .clip(RoundedCornerShape(10.dp))
                .background(Color(0xFF182A31))
                .border(1.dp, Color(0x4D54E8D2), RoundedCornerShape(10.dp))
                .padding(horizontal = 15.dp, vertical = 11.dp)
                .testTag("toast_banner"),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = null,
                tint = Color(0xFF57EAD4),
                modifier = Modifier.size(16.dp)
            )
            Text(
                text = message,
                color = Color(0xFFBBFFF3),
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Composable
fun CrownDrawer(
    open: Boolean,
    uiState: CrownVaultUiState,
    onClose: () -> Unit,
    onNavigate: (Screen) -> Unit,
    onNotifications: () -> Unit,
    onOpenLegal: (LegalDoc) -> Unit
) {
    val t = uiState.t
    val isLight = CrownColors.isLight
    AnimatedVisibility(
        visible = open,
        enter = fadeIn(tween(220)),
        exit = fadeOut(tween(220))
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xAD010207))
                .clickable(onClick = onClose)
                .testTag("drawer_backdrop")
        )
    }

    AnimatedVisibility(
        visible = open,
        enter = slideInHorizontally(initialOffsetX = { -it }, animationSpec = tween(260)),
        exit = slideOutHorizontally(targetOffsetX = { -it }, animationSpec = tween(240))
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .widthIn(max = 320.dp)
                .fillMaxWidth(0.84f)
                .background(
                    Brush.linearGradient(
                        colors = if (isLight) {
                            listOf(Color(0xFFFFFFFF), Color(0xFFF0F5FC))
                        } else {
                            listOf(Color(0xFF14152B), Color(0xFF0A0B18))
                        }
                    )
                )
                .border(width = 1.dp, color = CrownColors.SurfaceBorder)
                .clickable(enabled = false) {}
                .statusBarsPadding()
                .navigationBarsPadding()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 20.dp, vertical = 18.dp)
            ) {
                // Header with Crown Vault logo avatar + user details
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(11.dp),
                        modifier = Modifier
                            .clip(RoundedCornerShape(12.dp))
                            .clickable { onNavigate(Screen.PROFILE) }
                            .padding(end = 6.dp)
                            .testTag("drawer_profile_header")
                    ) {
                        Box(
                            modifier = Modifier
                                .size(42.dp)
                                .clip(RoundedCornerShape(14.dp))
                                .background(Color(0x145BEEDB))
                                .border(1.dp, Color(0x4D5BEEDB), RoundedCornerShape(14.dp)),
                            contentAlignment = Alignment.Center
                        ) {
                            CrownBrandIcon(size = 34.dp)
                        }
                        Column {
                            Text(
                                text = uiState.displayName,
                                color = CrownColors.TextPrimary,
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Medium
                            )
                            Text(
                                text = uiState.walletAddressShort,
                                color = CrownColors.TextMuted,
                                fontSize = 10.sp,
                                fontFamily = FontFamily.Monospace
                            )
                        }
                    }

                    IconButton(
                        onClick = onClose,
                        modifier = Modifier
                            .size(48.dp)
                            .testTag("close_drawer_button")
                    ) {
                        Box(
                            modifier = Modifier
                                .size(34.dp)
                                .clip(RoundedCornerShape(11.dp))
                                .background(CrownColors.SurfaceGlass),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = stringResource(R.string.content_desc_close),
                                tint = CrownColors.TextSecondary,
                                modifier = Modifier.size(18.dp)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(18.dp))

                // Drawer balance card
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(15.dp))
                        .background(
                            Brush.linearGradient(
                                colors = listOf(Color(0x1A42E2C7), Color(0x1A9464FF))
                            )
                        )
                        .border(1.dp, Color(0x2958E6D2), RoundedCornerShape(15.dp))
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    Text(
                        text = t.drawer.vaultBalance,
                        color = CrownColors.TextMuted,
                        fontSize = 9.sp,
                        fontFamily = FontFamily.Monospace,
                        letterSpacing = 1.4.sp
                    )
                    Text(
                        text = "$38,190.54",
                        color = CrownColors.TextPrimary,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Medium
                    )
                    Text(
                        text = "+$2,184.32 · 6.06%",
                        color = CrownColors.PositiveMint,
                        fontSize = 11.sp,
                        fontFamily = FontFamily.Monospace
                    )
                }

                Spacer(modifier = Modifier.height(18.dp))

                DrawerNavRow(
                    icon = Icons.Outlined.Tune,
                    title = t.drawer.settings,
                    detail = t.drawer.settingsDetail,
                    tag = "drawer_item_settings",
                    onClick = { onNavigate(Screen.SYSTEM) }
                )
                Spacer(modifier = Modifier.height(6.dp))
                DrawerNavRow(
                    icon = Icons.Outlined.VerifiedUser,
                    title = t.drawer.security,
                    detail = t.drawer.securityDetail,
                    tag = "drawer_item_security",
                    onClick = { onNavigate(Screen.SECURITY) }
                )
                Spacer(modifier = Modifier.height(6.dp))
                DrawerNavRow(
                    icon = Icons.Outlined.Notifications,
                    title = t.drawer.notifications,
                    detail = t.drawer.notificationsDetail,
                    tag = "drawer_item_notifications",
                    onClick = onNotifications
                )
                Spacer(modifier = Modifier.height(6.dp))
                DrawerNavRow(
                    icon = Icons.Outlined.Person,
                    title = t.drawer.profile,
                    detail = "${uiState.displayName} · ${t.common.verified}",
                    tag = "drawer_item_profile",
                    onClick = { onNavigate(Screen.PROFILE) }
                )

                Spacer(modifier = Modifier.height(18.dp))
                HorizontalDivider(color = CrownColors.SurfaceBorder, thickness = 1.dp)
                Spacer(modifier = Modifier.height(14.dp))

                // Legal section
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    modifier = Modifier.padding(bottom = 8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Gavel,
                        contentDescription = null,
                        tint = CrownColors.TextMuted,
                        modifier = Modifier.size(14.dp)
                    )
                    Text(
                        text = t.drawer.legal.uppercase(),
                        color = CrownColors.TextMuted,
                        fontSize = 9.sp,
                        fontFamily = FontFamily.Monospace,
                        letterSpacing = 1.4.sp
                    )
                }

                LegalItemRow(
                    label = t.legal.terms.title,
                    tag = "drawer_legal_terms",
                    onClick = { onOpenLegal(LegalDoc.TERMS) }
                )
                LegalItemRow(
                    label = t.legal.privacy.title,
                    tag = "drawer_legal_privacy",
                    onClick = { onOpenLegal(LegalDoc.PRIVACY) }
                )
                LegalItemRow(
                    label = t.legal.usage.title,
                    tag = "drawer_legal_usage",
                    onClick = { onOpenLegal(LegalDoc.USAGE) }
                )
                LegalItemRow(
                    label = t.legal.disclaimer.title,
                    tag = "drawer_legal_disclaimer",
                    onClick = { onOpenLegal(LegalDoc.DISCLAIMER) }
                )

                Spacer(modifier = Modifier.height(16.dp))
                HorizontalDivider(color = CrownColors.SurfaceBorder, thickness = 1.dp)
                Spacer(modifier = Modifier.height(14.dp))

                // Drawer Footer with aligned brand logo
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(7.dp)
                    ) {
                        CrownBrandIcon(size = 16.dp)
                        Box(
                            modifier = Modifier
                                .size(6.dp)
                                .clip(CircleShape)
                                .background(CrownColors.NeonCyan)
                        )
                        Text(
                            text = "Crownlink · 12 ms",
                            color = CrownColors.TextMuted,
                            fontSize = 9.sp,
                            fontFamily = FontFamily.Monospace
                        )
                    }
                    Text(
                        text = "v2.389.04",
                        color = CrownColors.TextSubtle,
                        fontSize = 9.sp,
                        fontFamily = FontFamily.Monospace
                    )
                }

                Spacer(modifier = Modifier.height(14.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    CrownBrandIcon(size = 15.dp)
                    Spacer(modifier = Modifier.width(7.dp))
                    Text(
                        text = stringResource(R.string.drawer_signature),
                        color = CrownColors.TextSubtle,
                        fontSize = 9.sp,
                        lineHeight = 14.sp,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

@Composable
private fun DrawerNavRow(
    icon: ImageVector,
    title: String,
    detail: String,
    tag: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 58.dp)
            .clip(RoundedCornerShape(13.dp))
            .background(CrownColors.SurfaceGlass)
            .border(1.dp, CrownColors.SurfaceBorder, RoundedCornerShape(13.dp))
            .clickable(onClick = onClick)
            .padding(horizontal = 11.dp, vertical = 10.dp)
            .testTag(tag),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Box(
            modifier = Modifier
                .size(36.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(Color(0x1A53EBD3)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = CrownColors.NeonCyan,
                modifier = Modifier.size(18.dp)
            )
        }
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                color = CrownColors.TextPrimary,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium
            )
            Text(
                text = detail,
                color = CrownColors.TextMuted,
                fontSize = 9.sp,
                fontFamily = FontFamily.Monospace
            )
        }
        Icon(
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
            contentDescription = null,
            tint = CrownColors.TextSubtle,
            modifier = Modifier.size(18.dp)
        )
    }
}

@Composable
fun LegalItemRow(
    label: String,
    tag: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 48.dp)
            .clip(RoundedCornerShape(11.dp))
            .clickable(onClick = onClick)
            .padding(horizontal = 10.dp, vertical = 8.dp)
            .testTag(tag),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Box(
            modifier = Modifier
                .size(28.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(Color(0x1ABE94FF)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Outlined.Description,
                contentDescription = null,
                tint = CrownColors.AccentViolet,
                modifier = Modifier.size(15.dp)
            )
        }
        Text(
            text = label,
            color = CrownColors.TextSecondary,
            fontSize = 11.sp,
            modifier = Modifier.weight(1f)
        )
        Icon(
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
            contentDescription = null,
            tint = CrownColors.TextSubtle,
            modifier = Modifier.size(16.dp)
        )
    }
}

@Composable
fun ActionAndInboxModal(
    modalType: ModalType,
    uiState: CrownVaultUiState,
    onClose: () -> Unit,
    onLangChange: (Lang) -> Unit,
    onRecipientAddressChange: (String) -> Unit,
    onNotify: (String) -> Unit
) {
    val t = uiState.t
    val context = LocalContext.current

    val kicker = when (modalType) {
        ModalType.LANG -> "LANGUAGE"
        ModalType.NOTIFY -> t.modal.inbox
        ModalType.SEND -> t.modal.transferOut
        ModalType.RECEIVE -> t.modal.transferIn
    }
    val heading = when (modalType) {
        ModalType.LANG -> t.system.language
        ModalType.NOTIFY -> t.modal.notifications
        ModalType.SEND -> t.modal.sendAssets
        ModalType.RECEIVE -> t.modal.receiveAssets
    }

    ModalSheetBackdrop(onClose = onClose) {
        ModalHeader(kicker = kicker, title = heading, onClose = onClose)

        when (modalType) {
            ModalType.LANG -> {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(max = 380.dp)
                        .verticalScroll(rememberScrollState()),
                    verticalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    Lang.entries.forEach { langOption ->
                        val isSelected = uiState.lang == langOption
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .heightIn(min = 48.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .background(
                                    if (isSelected) Color(0x1A53EBD3)
                                    else CrownColors.SurfaceGlass
                                )
                                .border(
                                    1.dp,
                                    if (isSelected) Color(0x4D53EBD3)
                                    else CrownColors.SurfaceBorder,
                                    RoundedCornerShape(12.dp)
                                )
                                .clickable { onLangChange(langOption) }
                                .padding(horizontal = 14.dp, vertical = 12.dp)
                                .testTag("lang_option_${langOption.code}"),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            Text(
                                text = langOption.nativeName,
                                color = if (isSelected) CrownColors.TextPrimary else CrownColors.TextSecondary,
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Medium,
                                modifier = Modifier.weight(1f)
                            )
                            Text(
                                text = langOption.code.uppercase(),
                                color = CrownColors.TextMuted,
                                fontSize = 10.sp,
                                fontFamily = FontFamily.Monospace
                            )
                            if (isSelected) {
                                Icon(
                                    imageVector = Icons.Default.Check,
                                    contentDescription = null,
                                    tint = CrownColors.NeonCyan,
                                    modifier = Modifier.size(16.dp)
                                )
                            }
                        }
                    }
                }
            }

            ModalType.NOTIFY -> {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 28.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .size(52.dp)
                            .clip(CircleShape)
                            .background(Color(0x1F8C80FF)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Notifications,
                            contentDescription = null,
                            tint = Color(0xFF8C80FF),
                            modifier = Modifier.size(24.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(15.dp))
                    Text(
                        text = t.modal.allCaughtUp,
                        color = CrownColors.TextPrimary,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = t.modal.newActivity,
                        color = CrownColors.TextMuted,
                        fontSize = 12.sp
                    )
                }
            }

            ModalType.SEND, ModalType.RECEIVE -> {
                val isReceive = modalType == ModalType.RECEIVE
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(176.dp)
                        .clip(RoundedCornerShape(15.dp))
                        .background(Color.Black.copy(alpha = 0.25f))
                        .border(1.dp, CrownColors.SurfaceBorder, RoundedCornerShape(15.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    if (isReceive) {
                        QrMatrixGraphic(size = 116.dp, color = CrownColors.NeonCyan)
                    } else {
                        Icon(
                            imageVector = Icons.Default.NorthEast,
                            contentDescription = null,
                            tint = CrownColors.NeonCyan,
                            modifier = Modifier.size(40.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                if (isReceive) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(min = 48.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(Color.Black.copy(alpha = 0.28f))
                            .padding(horizontal = 13.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = uiState.walletAddressFull,
                            color = CrownColors.TextSecondary,
                            fontSize = 11.sp,
                            fontFamily = FontFamily.Monospace,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.weight(1f).padding(end = 8.dp)
                        )
                        IconButton(
                            onClick = {
                                copyToClipboard(context, uiState.walletAddressFull)
                                onNotify("Address copied to clipboard!")
                            },
                            modifier = Modifier.testTag("copy_wallet_address_button")
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.ContentCopy,
                                contentDescription = stringResource(R.string.content_desc_copy_address),
                                tint = CrownColors.NeonCyan,
                                modifier = Modifier.size(16.dp)
                            )
                        }
                    }
                } else {
                    OutlinedTextField(
                        value = uiState.recipientAddress,
                        onValueChange = onRecipientAddressChange,
                        placeholder = {
                            Text(
                                text = t.modal.pasteAddress,
                                color = CrownColors.TextMuted,
                                fontSize = 12.sp,
                                fontFamily = FontFamily.Monospace
                            )
                        },
                        singleLine = true,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = CrownColors.NeonCyan.copy(alpha = 0.5f),
                            unfocusedBorderColor = CrownColors.SurfaceBorder,
                            focusedContainerColor = Color.Black.copy(alpha = 0.28f),
                            unfocusedContainerColor = Color.Black.copy(alpha = 0.28f),
                            focusedTextColor = CrownColors.TextPrimary,
                            unfocusedTextColor = CrownColors.TextPrimary
                        ),
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("send_address_input")
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                CrownPrimaryButton(
                    text = if (isReceive) t.modal.copyAddress else t.modal.continueBtn,
                    tag = if (isReceive) "modal_copy_address_cta" else "modal_send_continue_cta",
                    onClick = {
                        if (isReceive) {
                            copyToClipboard(context, uiState.walletAddressFull)
                            onClose()
                            onNotify("Address copied to clipboard!")
                        } else {
                            onClose()
                            onNotify(t.modal.transferPrepared)
                        }
                    }
                )
            }
        }
    }
}

@Composable
private fun QrMatrixGraphic(size: Dp, color: Color) {
    Canvas(modifier = Modifier.size(size)) {
        val cells = 9
        val cellSize = this.size.width / cells
        val pattern = listOf(
            "111010111",
            "101001101",
            "111010111",
            "000111000",
            "101101101",
            "001010100",
            "111001101",
            "101010010",
            "111011101"
        )
        for (r in 0 until cells) {
            for (c in 0 until cells) {
                if (pattern[r][c] == '1') {
                    drawRoundRect(
                        color = color,
                        topLeft = Offset(c * cellSize + 1.5f, r * cellSize + 1.5f),
                        size = Size(cellSize - 3f, cellSize - 3f),
                        cornerRadius = CornerRadius(3f, 3f)
                    )
                }
            }
        }
    }
}

@Composable
fun LegalModal(
    doc: LegalDoc,
    t: TranslationSet,
    onClose: () -> Unit
) {
    val content = t.legal.forDoc(doc)
    ModalSheetBackdrop(onClose = onClose) {
        ModalHeader(kicker = "LEGAL", title = content.title, onClose = onClose)
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(max = 360.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            content.paragraphs.forEach { paragraph ->
                Text(
                    text = paragraph,
                    color = CrownColors.TextSecondary,
                    fontSize = 12.sp,
                    lineHeight = 19.sp
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        HorizontalDivider(color = CrownColors.SurfaceBorder, thickness = 1.dp)
        Spacer(modifier = Modifier.height(12.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            CrownBrandIcon(size = 15.dp)
            Spacer(modifier = Modifier.width(6.dp))
            Text(
                text = stringResource(R.string.legal_signature),
                color = CrownColors.TextSubtle,
                fontSize = 10.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun VaultPlusModal(
    uiState: CrownVaultUiState,
    onPlanChange: (Plan) -> Unit,
    onClose: () -> Unit,
    onActivate: () -> Unit
) {
    val t = uiState.t
    val features = listOf(
        Triple(Icons.Outlined.Bolt, t.vaultPlus.featureInstantTitle, t.vaultPlus.featureInstantDetail),
        Triple(Icons.Outlined.CardGiftcard, t.vaultPlus.featureStakingTitle, t.vaultPlus.featureStakingDetail),
        Triple(Icons.Outlined.Hub, t.vaultPlus.featurePriorityTitle, t.vaultPlus.featurePriorityDetail),
        Triple(Icons.Outlined.VerifiedUser, t.vaultPlus.featureShieldTitle, t.vaultPlus.featureShieldDetail),
        Triple(Icons.Outlined.CreditCard, t.vaultPlus.featureCardTitle, t.vaultPlus.featureCardDetail)
    )

    ModalSheetBackdrop(onClose = onClose) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
        ) {
            ModalHeader(kicker = "VAULT+", title = t.vaultPlus.header, onClose = onClose)

            Row(
                modifier = Modifier
                    .clip(RoundedCornerShape(20.dp))
                    .background(
                        Brush.horizontalGradient(
                            colors = listOf(Color(0x38B670FF), Color(0x2E56EDDA))
                        )
                    )
                    .border(1.dp, Color(0x52B670FF), RoundedCornerShape(20.dp))
                    .padding(horizontal = 14.dp, vertical = 7.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Icon(
                    imageVector = Icons.Outlined.AutoAwesome,
                    contentDescription = null,
                    tint = CrownColors.AccentViolet,
                    modifier = Modifier.size(14.dp)
                )
                Text(
                    text = t.vaultPlus.badge,
                    color = CrownColors.TextPrimary,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Spacer(modifier = Modifier.height(18.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                PlanOptionCard(
                    modifier = Modifier.weight(1f),
                    label = t.vaultPlus.monthly,
                    price = "$9.99",
                    unit = t.vaultPlus.perMonth,
                    badge = null,
                    selected = uiState.vaultPlusPlan == Plan.MONTHLY,
                    tag = "plan_monthly_button",
                    onClick = { onPlanChange(Plan.MONTHLY) }
                )
                PlanOptionCard(
                    modifier = Modifier.weight(1f),
                    label = t.vaultPlus.annual,
                    price = "$89.99",
                    unit = t.vaultPlus.perYear,
                    badge = t.vaultPlus.save25,
                    selected = uiState.vaultPlusPlan == Plan.ANNUAL,
                    tag = "plan_annual_button",
                    onClick = { onPlanChange(Plan.ANNUAL) }
                )
            }

            Spacer(modifier = Modifier.height(18.dp))

            features.forEach { (icon, title, detail) ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(34.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(Color(0x1753EBD3)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = icon,
                            contentDescription = null,
                            tint = CrownColors.NeonCyan,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = title,
                            color = CrownColors.TextPrimary,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium
                        )
                        Text(
                            text = detail,
                            color = CrownColors.TextMuted,
                            fontSize = 10.sp,
                            lineHeight = 14.sp
                        )
                    }
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = null,
                        tint = CrownColors.NeonCyan,
                        modifier = Modifier.size(16.dp)
                    )
                }
                HorizontalDivider(color = CrownColors.SurfaceBorder, thickness = 1.dp)
            }

            Spacer(modifier = Modifier.height(14.dp))
            Text(
                text = t.vaultPlus.freeTierNote,
                color = CrownColors.TextMuted,
                fontSize = 10.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(12.dp))

            CrownPrimaryButton(
                text = t.vaultPlus.ctaButton,
                icon = Icons.Outlined.AutoAwesome,
                tag = "activate_vault_plus_cta",
                onClick = onActivate
            )

            Spacer(modifier = Modifier.height(12.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                CrownBrandIcon(size = 15.dp)
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = t.vaultPlus.footer,
                    color = CrownColors.TextSubtle,
                    fontSize = 10.sp,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
fun CrownBrandFooter(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 24.dp, bottom = 8.dp)
            .testTag("app_brand_footer"),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        HorizontalDivider(color = CrownColors.SurfaceBorder, thickness = 1.dp)
        Spacer(modifier = Modifier.height(6.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            CrownBrandIcon(size = 20.dp)
            Text(
                text = buildAnnotatedString {
                    withStyle(SpanStyle(color = CrownColors.TextPrimary, fontWeight = FontWeight.Bold)) {
                        append(stringResource(R.string.brand_crown))
                    }
                    withStyle(SpanStyle(color = CrownColors.NeonCyan, fontWeight = FontWeight.Bold)) {
                        append(stringResource(R.string.brand_vault))
                    }
                },
                fontSize = 10.sp,
                letterSpacing = 2.2.sp
            )
            Text(
                text = "·",
                color = CrownColors.TextSubtle,
                fontSize = 10.sp
            )
            Text(
                text = stringResource(R.string.legal_signature),
                color = CrownColors.TextMuted,
                fontSize = 10.sp,
                fontFamily = FontFamily.Monospace
            )
        }
    }
}

@Composable
private fun PlanOptionCard(
    modifier: Modifier = Modifier,
    label: String,
    price: String,
    unit: String,
    badge: String?,
    selected: Boolean,
    tag: String,
    onClick: () -> Unit
) {
    Box(modifier = modifier) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(14.dp))
                .background(
                    if (selected) Brush.linearGradient(
                        listOf(Color(0x1F38E0D1), Color(0x1F7B4EE4))
                    )
                    else Brush.linearGradient(
                        listOf(CrownColors.SurfaceGlass, CrownColors.SurfaceGlass)
                    )
                )
                .border(
                    1.dp,
                    if (selected) Color(0x6653EBD3) else CrownColors.SurfaceBorder,
                    RoundedCornerShape(14.dp)
                )
                .clickable(onClick = onClick)
                .padding(vertical = 15.dp, horizontal = 10.dp)
                .testTag(tag),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Text(
                text = label,
                color = if (selected) CrownColors.NeonCyan else CrownColors.TextMuted,
                fontSize = 10.sp,
                fontFamily = FontFamily.Monospace
            )
            Text(
                text = buildAnnotatedString {
                    withStyle(SpanStyle(color = CrownColors.TextPrimary, fontSize = 21.sp, fontWeight = FontWeight.SemiBold)) {
                        append(price)
                    }
                    withStyle(SpanStyle(color = CrownColors.TextMuted, fontSize = 11.sp, fontFamily = FontFamily.Monospace)) {
                        append(unit)
                    }
                }
            )
        }

        if (badge != null) {
            Box(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .offset(x = (-8).dp, y = (-7).dp)
                    .clip(RoundedCornerShape(6.dp))
                    .background(
                        Brush.horizontalGradient(
                            listOf( Color(0xFF56EDDA), CrownColors.NeonAqua)
                        )
                    )
                    .padding(horizontal = 7.dp, vertical = 2.dp)
            ) {
                Text(
                    text = badge,
                    color = CrownColors.DarkInk,
                    fontSize = 8.sp,
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
fun SettingsModal(
    type: SettingsModalType,
    uiState: CrownVaultUiState,
    onClose: () -> Unit,
    onSelectTheme: (String) -> Unit,
    onSelectNetwork: (String) -> Unit,
    onToggleSmart: () -> Unit,
    onTogglePrice: () -> Unit,
    onToggleSecurity: () -> Unit,
    onSaveNotifications: () -> Unit
) {
    val t = uiState.t
    val title = when (type) {
        SettingsModalType.APPEARANCE -> t.system.appearanceTitle
        SettingsModalType.NETWORK -> t.system.networkTitle
        SettingsModalType.NOTIFICATIONS -> t.system.notificationsTitle
    }

    ModalSheetBackdrop(onClose = onClose) {
        ModalHeader(kicker = "SETTINGS", title = title, onClose = onClose)

        when (type) {
            SettingsModalType.APPEARANCE -> {
                Column(verticalArrangement = Arrangement.spacedBy(7.dp)) {
                    SettingsSelectOption(
                        icon = Icons.Outlined.DarkMode,
                        label = t.system.themeMidnight,
                        selected = uiState.themeMode == "midnight",
                        tag = "theme_option_midnight",
                        onClick = { onSelectTheme("midnight") }
                    )
                    SettingsSelectOption(
                        icon = Icons.Outlined.AutoAwesome,
                        label = t.system.themeNeon,
                        selected = uiState.themeMode == "neon",
                        tag = "theme_option_neon",
                        onClick = { onSelectTheme("neon") }
                    )
                    SettingsSelectOption(
                        icon = Icons.Outlined.Tune,
                        label = t.system.themeAuto,
                        selected = uiState.themeMode == "auto",
                        tag = "theme_option_auto",
                        onClick = { onSelectTheme("auto") }
                    )
                }
            }

            SettingsModalType.NETWORK -> {
                Column(verticalArrangement = Arrangement.spacedBy(7.dp)) {
                    SettingsSelectOption(
                        icon = Icons.Outlined.Hub,
                        label = t.system.netCrownlink,
                        selected = uiState.networkPref == "crownlink",
                        tag = "network_option_crownlink",
                        onClick = { onSelectNetwork("crownlink") }
                    )
                    SettingsSelectOption(
                        icon = Icons.Outlined.Hub,
                        label = t.system.netQuantumMesh,
                        selected = uiState.networkPref == "quantum",
                        tag = "network_option_quantum",
                        onClick = { onSelectNetwork("quantum") }
                    )
                    SettingsSelectOption(
                        icon = Icons.Outlined.Hub,
                        label = t.system.netDirectLink,
                        selected = uiState.networkPref == "direct",
                        tag = "network_option_direct",
                        onClick = { onSelectNetwork("direct") }
                    )
                }
            }

            SettingsModalType.NOTIFICATIONS -> {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    SettingsToggleRow(
                        title = t.system.notifSmartAlerts,
                        detail = t.system.notifSmartAlertsDetail,
                        checked = uiState.notifSmart,
                        tag = "toggle_smart_alerts",
                        onToggle = onToggleSmart
                    )
                    SettingsToggleRow(
                        title = t.system.notifPriceAlerts,
                        detail = t.system.notifPriceAlertsDetail,
                        checked = uiState.notifPrice,
                        tag = "toggle_price_alerts",
                        onToggle = onTogglePrice
                    )
                    SettingsToggleRow(
                        title = t.system.notifSecurityAlerts,
                        detail = t.system.notifSecurityAlertsDetail,
                        checked = uiState.notifSecurity,
                        tag = "toggle_security_alerts",
                        onToggle = onToggleSecurity
                    )

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 6.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(Color(0x0F53EBD3))
                            .border(1.dp, Color(0x2653EBD3), RoundedCornerShape(10.dp))
                            .padding(horizontal = 14.dp, vertical = 10.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = null,
                            tint = CrownColors.NeonCyan,
                            modifier = Modifier.size(14.dp)
                        )
                        Text(
                            text = t.system.notifPushEnabled,
                            color = CrownColors.PositiveMint,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }

                    CrownPrimaryButton(
                        text = t.system.notifSettingsSaved,
                        icon = Icons.Default.Check,
                        tag = "save_notifications_button",
                        onClick = onSaveNotifications
                    )
                }
            }
        }
    }
}

@Composable
private fun SettingsSelectOption(
    icon: ImageVector,
    label: String,
    selected: Boolean,
    tag: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 50.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(if (selected) Color(0x1A53EBD3) else CrownColors.SurfaceGlass)
            .border(
                1.dp,
                if (selected) Color(0x4D53EBD3) else CrownColors.SurfaceBorder,
                RoundedCornerShape(12.dp)
            )
            .clickable(onClick = onClick)
            .padding(horizontal = 14.dp, vertical = 13.dp)
            .testTag(tag),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = if (selected) CrownColors.NeonCyan else CrownColors.TextPrimary,
            modifier = Modifier.size(18.dp)
        )
        Text(
            text = label,
            color = if (selected) CrownColors.NeonCyan else CrownColors.TextPrimary,
            fontSize = 13.sp,
            modifier = Modifier.weight(1f)
        )
        if (selected) {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = null,
                tint = CrownColors.NeonCyan,
                modifier = Modifier.size(16.dp)
            )
        }
    }
}

@Composable
private fun SettingsToggleRow(
    title: String,
    detail: String,
    checked: Boolean,
    tag: String,
    onToggle: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(CrownColors.SurfaceGlass)
            .border(1.dp, CrownColors.SurfaceBorder, RoundedCornerShape(12.dp))
            .clickable(onClick = onToggle)
            .padding(horizontal = 14.dp, vertical = 12.dp)
            .testTag(tag),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f).padding(end = 10.dp)) {
            Text(
                text = title,
                color = CrownColors.TextPrimary,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium
            )
            Spacer(modifier = Modifier.height(3.dp))
            Text(
                text = detail,
                color = CrownColors.TextMuted,
                fontSize = 10.sp,
                fontFamily = FontFamily.Monospace
            )
        }

        Switch(
            checked = checked,
            onCheckedChange = { onToggle() },
            colors = SwitchDefaults.colors(
                checkedThumbColor = CrownColors.NeonCyan,
                checkedTrackColor = Color(0x4D53EBD3),
                uncheckedThumbColor = CrownColors.TextSecondary,
                uncheckedTrackColor = CrownColors.SurfaceBorder
            )
        )
    }
}

@Composable
private fun ModalSheetBackdrop(
    onClose: () -> Unit,
    content: @Composable ColumnScope.() -> Unit
) {
    val isLight = CrownColors.isLight
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xAD010207))
            .clickable(onClick = onClose)
            .padding(16.dp)
            .navigationBarsPadding(),
        contentAlignment = Alignment.BottomCenter
    ) {
        Column(
            modifier = Modifier
                .widthIn(max = 448.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(22.dp))
                .background(
                    Brush.linearGradient(
                        colors = if (isLight) {
                            listOf(Color(0xFFFFFFFF), Color(0xFFF1F5FC))
                        } else {
                            listOf(Color(0xFF1A1B36), Color(0xFF0C0E1C))
                        }
                    )
                )
                .border(1.dp, CrownColors.SurfaceBorder, RoundedCornerShape(22.dp))
                .clickable(enabled = false) {}
                .padding(22.dp),
            content = content
        )
    }
}

@Composable
private fun ModalHeader(
    kicker: String,
    title: String,
    onClose: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 18.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Top
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = kicker,
                color = CrownColors.TextMuted,
                fontSize = 9.sp,
                fontFamily = FontFamily.Monospace,
                letterSpacing = 1.5.sp
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = title,
                color = CrownColors.TextPrimary,
                fontSize = 21.sp,
                fontWeight = FontWeight.Medium
            )
        }
        IconButton(
            onClick = onClose,
            modifier = Modifier
                .size(48.dp)
                .testTag("modal_close_button")
        ) {
            Box(
                modifier = Modifier
                    .size(34.dp)
                    .clip(RoundedCornerShape(11.dp))
                    .background(CrownColors.SurfaceGlass),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = stringResource(R.string.content_desc_close),
                    tint = CrownColors.TextSecondary,
                    modifier = Modifier.size(18.dp)
                )
            }
        }
    }
}

@Composable
fun CrownPrimaryButton(
    text: String,
    tag: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    icon: ImageVector? = null,
    isSellStyle: Boolean = false,
    enabled: Boolean = true
) {
    val gradientColors = if (isSellStyle) {
        listOf(Color(0xFFFF7B7B), Color(0xFFFFA0A0))
    } else {
        listOf(Color(0xFF56EDDA), Color(0xFFA1EFFF))
    }
    val textColor = if (isSellStyle) Color(0xFF2A0A0A) else CrownColors.DarkInk

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(48.dp)
            .clip(RoundedCornerShape(13.dp))
            .background(Brush.horizontalGradient(gradientColors))
            .clickable(enabled = enabled, onClick = onClick)
            .padding(horizontal = 16.dp)
            .testTag(tag),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (icon != null) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = textColor,
                modifier = Modifier.size(18.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
        }
        Text(
            text = text,
            color = textColor,
            fontSize = 13.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}

fun copyToClipboard(context: Context, text: String) {
    val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as? ClipboardManager
    clipboard?.setPrimaryClip(ClipData.newPlainText("Crown Vault", text))
}

@Composable
fun ProfileInteractiveModal(
    modalType: ProfileModalType,
    uiState: CrownVaultUiState,
    onClose: () -> Unit,
    onSaveDisplayName: (String) -> Unit,
    onNotify: (String) -> Unit
) {
    val context = LocalContext.current

    ModalSheetBackdrop(onClose = onClose) {
        when (modalType) {
            ProfileModalType.EDIT_NAME -> {
                var draftName by remember(uiState.displayName) { mutableStateOf(uiState.displayName) }

                ModalHeader(
                    kicker = "IDENTITY PROFILE",
                    title = "Edit Display Name",
                    onClose = onClose
                )

                Text(
                    text = "Update your public Crown Vault handle. Changes sync immediately across your Profile card, top identity banner, and navigation drawer.",
                    color = CrownColors.TextMuted,
                    fontSize = 11.sp,
                    lineHeight = 17.sp
                )

                Spacer(modifier = Modifier.height(14.dp))

                Text(
                    text = "DISPLAY NAME",
                    color = CrownColors.NeonCyan,
                    fontSize = 9.sp,
                    fontFamily = FontFamily.Monospace,
                    letterSpacing = 1.4.sp
                )

                Spacer(modifier = Modifier.height(6.dp))

                OutlinedTextField(
                    value = draftName,
                    onValueChange = { draftName = it },
                    placeholder = {
                        Text(
                            text = "Enter display name...",
                            color = CrownColors.TextMuted,
                            fontSize = 13.sp
                        )
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Outlined.Person,
                            contentDescription = null,
                            tint = CrownColors.NeonCyan,
                            modifier = Modifier.size(18.dp)
                        )
                    },
                    singleLine = true,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = CrownColors.NeonCyan,
                        unfocusedBorderColor = CrownColors.SurfaceBorder,
                        focusedContainerColor = Color.Black.copy(alpha = 0.32f),
                        unfocusedContainerColor = Color.Black.copy(alpha = 0.25f),
                        focusedTextColor = CrownColors.TextPrimary,
                        unfocusedTextColor = CrownColors.TextPrimary
                    ),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("edit_display_name_input")
                )

                Spacer(modifier = Modifier.height(18.dp))

                CrownPrimaryButton(
                    text = "Save Name",
                    icon = Icons.Default.Check,
                    tag = "save_display_name_button",
                    onClick = { onSaveDisplayName(draftName) }
                )
            }

            ProfileModalType.VERIFICATION_STATUS -> {
                ModalHeader(
                    kicker = "SECURITY CLEARANCE",
                    title = "Verification Status",
                    onClose = onClose
                )

                // Cyberpunk Verified Tier 3 Hero Banner
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(15.dp))
                        .background(
                            Brush.linearGradient(
                                colors = listOf(Color(0x2942E2C7), Color(0x267B4EE4))
                            )
                        )
                        .border(1.dp, Color(0x5953EBD3), RoundedCornerShape(15.dp))
                        .padding(16.dp)
                        .testTag("verification_status_hero"),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(13.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(48.dp)
                            .clip(CircleShape)
                            .background(Color(0x264BE3B5))
                            .border(1.dp, CrownColors.NeonCyan, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.CheckCircle,
                            contentDescription = null,
                            tint = CrownColors.PositiveMint,
                            modifier = Modifier.size(26.dp)
                        )
                    }
                    Column(modifier = Modifier.weight(1f)) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Text(
                                text = "Verified (Tier 3)",
                                color = CrownColors.TextPrimary,
                                fontSize = 15.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(4.dp))
                                    .background(Color(0x264BE3B5))
                                    .padding(horizontal = 6.dp, vertical = 2.dp)
                            ) {
                                Text(
                                    text = "ACTIVE",
                                    color = CrownColors.PositiveMint,
                                    fontSize = 8.sp,
                                    fontFamily = FontFamily.Monospace,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "Zero-Knowledge Institutional Clearance · Max Tier",
                            color = CrownColors.TextMuted,
                            fontSize = 10.sp,
                            fontFamily = FontFamily.Monospace
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "TIER 3 ACTIVE PERKS",
                    color = CrownColors.TextMuted,
                    fontSize = 9.sp,
                    fontFamily = FontFamily.Monospace,
                    letterSpacing = 1.4.sp
                )

                Spacer(modifier = Modifier.height(8.dp))

                val tierPerks = listOf(
                    Triple(
                        Icons.Outlined.Bolt,
                        "Unlimited Crypto Transactions",
                        "Unrestricted daily & monthly cross-chain volume with zero caps"
                    ),
                    Triple(
                        Icons.Outlined.AccountBalanceWallet,
                        "Zero-Friction On-Ramp",
                        "Instant fiat-to-crypto settlement with priority liquidity routing"
                    ),
                    Triple(
                        Icons.Outlined.VerifiedUser,
                        "Institutional Shielding Active",
                        "Multi-party quantum-resistant custody & real-time threat protection"
                    )
                )

                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    tierPerks.forEach { (perkIcon, perkTitle, perkDetail) ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(12.dp))
                                .background(Color.White.copy(alpha = 0.04f))
                                .border(1.dp, Color.White.copy(alpha = 0.07f), RoundedCornerShape(12.dp))
                                .padding(horizontal = 12.dp, vertical = 11.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(11.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(34.dp)
                                    .clip(RoundedCornerShape(9.dp))
                                    .background(Color(0x1A53EBD3)),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = perkIcon,
                                    contentDescription = null,
                                    tint = CrownColors.NeonCyan,
                                    modifier = Modifier.size(18.dp)
                                )
                            }
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = perkTitle,
                                    color = CrownColors.TextPrimary,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.SemiBold
                                )
                                Spacer(modifier = Modifier.height(2.dp))
                                Text(
                                    text = perkDetail,
                                    color = CrownColors.TextMuted,
                                    fontSize = 10.sp,
                                    lineHeight = 14.sp
                                )
                            }
                            Icon(
                                imageVector = Icons.Default.CheckCircle,
                                contentDescription = null,
                                tint = CrownColors.PositiveMint,
                                modifier = Modifier.size(18.dp)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(18.dp))

                CrownPrimaryButton(
                    text = "Got it",
                    icon = Icons.Default.Check,
                    tag = "verification_modal_got_it_button",
                    onClick = onClose
                )
            }

            ProfileModalType.RECEIVE_WALLET -> {
                ModalHeader(
                    kicker = "RECEIVE FUNDS",
                    title = "Receive Crypto",
                    onClose = onClose
                )

                // Clean QR Code Container with Cyberpunk Frame
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(196.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(
                            Brush.radialGradient(
                                colors = listOf(Color(0x2653EBD3), Color(0xD9070812))
                            )
                        )
                        .border(1.dp, Color(0x4D53EBD3), RoundedCornerShape(16.dp))
                        .testTag("receive_modal_qr_container"),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(136.dp)
                                .clip(RoundedCornerShape(14.dp))
                                .background(Color(0xFF0A0C1B))
                                .border(1.dp, Color(0x5953EBD3), RoundedCornerShape(14.dp))
                                .padding(12.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            QrMatrixGraphic(size = 110.dp, color = CrownColors.NeonCyan)
                            CrownBrandIcon(size = 24.dp)
                        }
                        Text(
                            text = "SCAN TO DEPOSIT · CROWNLINK MULTICHAIN",
                            color = CrownColors.NeonCyan,
                            fontSize = 9.sp,
                            fontFamily = FontFamily.Monospace,
                            letterSpacing = 1.1.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                Text(
                    text = "PUBLIC WALLET ADDRESS",
                    color = CrownColors.TextMuted,
                    fontSize = 9.sp,
                    fontFamily = FontFamily.Monospace,
                    letterSpacing = 1.4.sp
                )

                Spacer(modifier = Modifier.height(6.dp))

                // Full Public Wallet Address Box
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color.Black.copy(alpha = 0.34f))
                        .border(1.dp, Color(0x4053EBD3), RoundedCornerShape(12.dp))
                        .clickable {
                            copyToClipboard(context, uiState.walletAddressFull)
                            onNotify("Address copied to clipboard!")
                        }
                        .padding(horizontal = 13.dp, vertical = 12.dp)
                        .testTag("receive_modal_full_address"),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = uiState.walletAddressFull,
                        color = CrownColors.TextPrimary,
                        fontSize = 11.sp,
                        fontFamily = FontFamily.Monospace,
                        lineHeight = 16.sp,
                        modifier = Modifier
                            .weight(1f)
                            .padding(end = 10.dp)
                    )
                    Icon(
                        imageVector = Icons.Outlined.ContentCopy,
                        contentDescription = stringResource(R.string.content_desc_copy_address),
                        tint = CrownColors.NeonCyan,
                        modifier = Modifier.size(18.dp)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                CrownPrimaryButton(
                    text = "Copy Address",
                    icon = Icons.Outlined.ContentCopy,
                    tag = "receive_modal_copy_address_button",
                    onClick = {
                        copyToClipboard(context, uiState.walletAddressFull)
                        onNotify("Address copied to clipboard!")
                    }
                )
            }
        }
    }
}
