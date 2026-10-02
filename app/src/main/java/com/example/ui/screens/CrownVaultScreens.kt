package com.example.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.model.*
import com.example.ui.components.*
import com.example.ui.theme.CrownColors
import com.example.viewmodel.CrownVaultUiState

@Composable
fun VaultScreen(
    uiState: CrownVaultUiState,
    onToggleBalanceHidden: () -> Unit,
    onAction: (ModalType) -> Unit,
    onNavigate: (Screen) -> Unit,
    onAssetClick: (Asset) -> Unit
) {
    val t = uiState.t
    Column(modifier = Modifier.fillMaxWidth()) {
        // Hero balance section
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(5.dp),
                modifier = Modifier
                    .clip(RoundedCornerShape(6.dp))
                    .clickable(onClick = onToggleBalanceHidden)
                    .padding(horizontal = 8.dp, vertical = 4.dp)
                    .testTag("toggle_balance_visibility_button")
            ) {
                Text(
                    text = t.vault.balanceLabel,
                    color = CrownColors.TextMuted,
                    fontSize = 10.sp,
                    fontFamily = FontFamily.Monospace,
                    letterSpacing = 1.5.sp
                )
                Text(
                    text = "◉",
                    color = CrownColors.TextSecondary,
                    fontSize = 10.sp
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = buildAnnotatedString {
                    if (uiState.balanceHidden) {
                        withStyle(
                            SpanStyle(
                                color = CrownColors.TextPrimary,
                                fontSize = 44.sp,
                                fontWeight = FontWeight.Medium
                            )
                        ) {
                            append("••••••••")
                        }
                    } else {
                        withStyle(
                            SpanStyle(
                                color = CrownColors.TextPrimary,
                                fontSize = 46.sp,
                                fontWeight = FontWeight.Medium,
                                letterSpacing = (-2.0).sp
                            )
                        ) {
                            append("$38,190")
                        }
                        withStyle(
                            SpanStyle(
                                color = Color(0xFF8D91AA),
                                fontSize = 25.sp,
                                fontWeight = FontWeight.Medium
                            )
                        ) {
                            append(".54")
                        }
                    }
                },
                modifier = Modifier.testTag("hero_balance_value")
            )

            Spacer(modifier = Modifier.height(6.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                        contentDescription = null,
                        tint = CrownColors.PositiveMint,
                        modifier = Modifier
                            .size(15.dp)
                            .rotate(-45f)
                    )
                    Text(
                        text = "+$2,184.32 (6.06%)",
                        color = CrownColors.PositiveMint,
                        fontSize = 11.sp,
                        fontFamily = FontFamily.Monospace
                    )
                }
                Text(
                    text = "24h",
                    color = CrownColors.TextMuted,
                    fontSize = 11.sp,
                    fontFamily = FontFamily.Monospace
                )
            }

            Spacer(modifier = Modifier.height(14.dp))

            VaultBalanceChart(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                listOf("00:00", "06:00", "12:00", "18:00", t.common.now).forEach { label ->
                    Text(
                        text = label,
                        color = CrownColors.TextSubtle,
                        fontSize = 9.sp,
                        fontFamily = FontFamily.Monospace
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(26.dp))

        // Action row: Send & Receive
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            CrownPrimaryButton(
                text = t.vault.send,
                icon = Icons.Default.NorthEast,
                tag = "vault_send_button",
                onClick = { onAction(ModalType.SEND) },
                modifier = Modifier.weight(1f)
            )

            Row(
                modifier = Modifier
                    .weight(1f)
                    .height(48.dp)
                    .clip(RoundedCornerShape(13.dp))
                    .background(Color.White.copy(alpha = 0.06f))
                    .border(1.dp, Color.White.copy(alpha = 0.10f), RoundedCornerShape(13.dp))
                    .clickable { onAction(ModalType.RECEIVE) }
                    .padding(horizontal = 16.dp)
                    .testTag("vault_receive_button"),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.SouthWest,
                    contentDescription = null,
                    tint = Color(0xFFDCE0F1),
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = t.vault.receive,
                    color = Color(0xFFDCE0F1),
                    fontSize = 13.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }

        Spacer(modifier = Modifier.height(30.dp))

        // Portfolio heading
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 14.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom
        ) {
            Column {
                Text(
                    text = t.vault.portfolio,
                    color = CrownColors.TextMuted,
                    fontSize = 9.sp,
                    fontFamily = FontFamily.Monospace,
                    letterSpacing = 1.5.sp
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = t.vault.yourAssets,
                    color = CrownColors.TextPrimary,
                    fontSize = 21.sp,
                    fontWeight = FontWeight.Medium
                )
            }

            Row(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .clickable { onNavigate(Screen.MARKETS) }
                    .padding(horizontal = 8.dp, vertical = 6.dp)
                    .testTag("vault_manage_button"),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(3.dp)
            ) {
                Text(
                    text = t.vault.manage,
                    color = CrownColors.TextMuted,
                    fontSize = 11.sp
                )
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                    contentDescription = null,
                    tint = CrownColors.TextMuted,
                    modifier = Modifier.size(15.dp)
                )
            }
        }

        // Asset list
        Column(verticalArrangement = Arrangement.spacedBy(7.dp)) {
            uiState.assets.forEach { asset ->
                AssetItemRow(
                    asset = asset,
                    onClick = { onAssetClick(asset) }
                )
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Quick Grid
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            QuickMiniCard(
                modifier = Modifier.weight(1f),
                icon = Icons.Outlined.Lock,
                iconTint = CrownColors.NeonCyan,
                iconBg = Color(0x1A53EBD3),
                title = t.vault.earnYield,
                detail = t.vault.earnYieldDetail,
                tag = "quick_card_staking",
                onClick = { onNavigate(Screen.STAKING) }
            )
            QuickMiniCard(
                modifier = Modifier.weight(1f),
                icon = Icons.Outlined.AutoAwesome,
                iconTint = CrownColors.AccentViolet,
                iconBg = Color(0x1ABE94FF),
                title = t.vault.vaultPlusPerks,
                detail = t.vault.vaultPlusPerksDetail,
                tag = "quick_card_plus",
                onClick = { onNavigate(Screen.PLUS) }
            )
        }
    }
}

@Composable
private fun AssetItemRow(
    asset: Asset,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 68.dp)
            .clip(RoundedCornerShape(14.dp))
            .background(Color.White.copy(alpha = 0.038f))
            .border(1.dp, Color.White.copy(alpha = 0.065f), RoundedCornerShape(14.dp))
            .clickable(onClick = onClick)
            .padding(horizontal = 12.dp, vertical = 10.dp)
            .testTag("asset_row_${asset.ticker.lowercase()}"),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        AssetIconBadge(asset = asset)
        Column(modifier = Modifier.weight(1f)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Text(
                    text = asset.name,
                    color = CrownColors.TextPrimary,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Medium
                )
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(4.dp))
                        .background(Color.White.copy(alpha = 0.06f))
                        .padding(horizontal = 5.dp, vertical = 1.dp)
                ) {
                    Text(
                        text = asset.ticker,
                        color = CrownColors.NeonCyan,
                        fontSize = 8.sp,
                        fontFamily = FontFamily.Monospace,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
            Spacer(modifier = Modifier.height(3.dp))
            Text(
                text = asset.amount,
                color = CrownColors.TextMuted,
                fontSize = 10.sp,
                fontFamily = FontFamily.Monospace
            )
        }
        AssetMiniSparkline(
            asset = asset,
            modifier = Modifier
                .width(46.dp)
                .height(22.dp)
        )
        Column(horizontalAlignment = Alignment.End) {
            Text(
                text = asset.value,
                color = CrownColors.TextPrimary,
                fontSize = 13.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = FontFamily.Monospace
            )
            Spacer(modifier = Modifier.height(3.dp))
            Text(
                text = asset.change,
                color = if (asset.isPositiveChange) CrownColors.PositiveMint else CrownColors.CoralAlert,
                fontSize = 10.sp,
                fontFamily = FontFamily.Monospace
            )
        }
        Icon(
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
            contentDescription = null,
            tint = CrownColors.NeonCyan.copy(alpha = 0.8f),
            modifier = Modifier.size(18.dp)
        )
    }
}

@Composable
private fun QuickMiniCard(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    iconTint: Color,
    iconBg: Color,
    title: String,
    detail: String,
    tag: String,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier
            .heightIn(min = 56.dp)
            .clip(RoundedCornerShape(13.dp))
            .background(Color.White.copy(alpha = 0.028f))
            .border(1.dp, Color.White.copy(alpha = 0.06f), RoundedCornerShape(13.dp))
            .clickable(onClick = onClick)
            .padding(horizontal = 10.dp, vertical = 11.dp)
            .testTag(tag),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Box(
            modifier = Modifier
                .size(30.dp)
                .clip(RoundedCornerShape(9.dp))
                .background(iconBg),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = iconTint,
                modifier = Modifier.size(16.dp)
            )
        }
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                color = Color(0xFFD6DAEA),
                fontSize = 11.sp,
                fontWeight = FontWeight.Medium,
                maxLines = 1
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = detail,
                color = CrownColors.TextMuted,
                fontSize = 9.sp,
                fontFamily = FontFamily.Monospace,
                maxLines = 1
            )
        }
        Icon(
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
            contentDescription = null,
            tint = CrownColors.TextMuted,
            modifier = Modifier.size(15.dp)
        )
    }
}

@Composable
fun MarketsScreen(
    uiState: CrownVaultUiState,
    onBack: () -> Unit,
    onTabChange: (String) -> Unit,
    onSelectTradeAsset: (Int) -> Unit,
    onTimeframeChange: (String) -> Unit,
    onChartStyleChange: (String) -> Unit,
    onQuickAction: (String) -> Unit,
    onTradeSideChange: (String) -> Unit,
    onOrderTypeChange: (String) -> Unit,
    onOrderAmountChange: (String) -> Unit,
    onOrderPercentSelect: (Double) -> Unit,
    onSubmitTrade: () -> Unit,
    onAddWatchlist: (Asset) -> Unit
) {
    BackHandler(onBack = onBack)
    val t = uiState.t
    val selectedAsset = uiState.selectedTradeAsset

    Column(modifier = Modifier.fillMaxWidth()) {
        PageIntroSection(
            kicker = t.markets.kicker,
            title = t.markets.title,
            subtitle = t.markets.subtitle
        )

        // Tab Toggle
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 18.dp)
                .clip(RoundedCornerShape(13.dp))
                .background(Color.White.copy(alpha = 0.04f))
                .border(1.dp, Color.White.copy(alpha = 0.06f), RoundedCornerShape(13.dp))
                .padding(4.dp),
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            MarketTabButton(
                modifier = Modifier.weight(1f),
                text = t.markets.marketOverview,
                icon = Icons.Outlined.BarChart,
                active = uiState.marketsTab == "overview",
                tag = "markets_tab_overview",
                onClick = { onTabChange("overview") }
            )
            MarketTabButton(
                modifier = Modifier.weight(1f),
                text = t.markets.activeTrading,
                icon = Icons.Outlined.Bolt,
                active = uiState.marketsTab == "trading",
                tag = "markets_tab_trading",
                onClick = { onTabChange("trading") }
            )
        }

        AnimatedContent(
            targetState = uiState.marketsTab,
            transitionSpec = {
                (fadeIn(tween(220)) + slideInVertically(tween(220)) { it / 12 })
                    .togetherWith(fadeOut(tween(160)))
            },
            label = "marketsTabTransition"
        ) { activeTab ->
            if (activeTab == "overview") {
                Column(modifier = Modifier.fillMaxWidth()) {
                    // Market Highlight Card
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 14.dp)
                            .clip(RoundedCornerShape(17.dp))
                            .background(
                                Brush.linearGradient(
                                    colors = listOf(Color(0x1F44E0D5), Color(0x217B4EE4))
                                )
                            )
                            .border(1.dp, Color(0x3350E6D5), RoundedCornerShape(17.dp))
                            .clickable {
                                onSelectTradeAsset(0)
                                onTabChange("trading")
                            }
                            .padding(18.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(4.dp))
                                    .background(Color(0x144AE4D1))
                                    .border(1.dp, Color(0x2B4AE4D1), RoundedCornerShape(4.dp))
                                    .padding(horizontal = 7.dp, vertical = 3.dp)
                            ) {
                                Text(
                                    text = t.markets.topMover,
                                    color = CrownColors.NeonCyan,
                                    fontSize = 9.sp,
                                    fontFamily = FontFamily.Monospace
                                )
                            }
                            Spacer(modifier = Modifier.height(12.dp))
                            Text(
                                text = t.markets.neonIndex,
                                color = CrownColors.TextPrimary,
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Medium
                            )
                            Text(
                                text = "+18.42%",
                                color = CrownColors.NeonCyan,
                                fontSize = 16.sp,
                                fontFamily = FontFamily.Monospace,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(top = 3.dp, bottom = 5.dp)
                            )
                            Text(
                                text = t.markets.globalSynth,
                                color = CrownColors.TextMuted,
                                fontSize = 10.sp
                            )
                        }

                        MarketSparkline(
                            modifier = Modifier
                                .width(116.dp)
                                .height(52.dp)
                        )
                    }

                    // Ranked market list — clicking any row opens that token in Active Trading
                    Column(verticalArrangement = Arrangement.spacedBy(7.dp)) {
                        uiState.assets.forEachIndexed { index, asset ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .heightIn(min = 66.dp)
                                    .clip(RoundedCornerShape(14.dp))
                                    .background(Color.White.copy(alpha = 0.035f))
                                    .border(1.dp, Color.White.copy(alpha = 0.055f), RoundedCornerShape(14.dp))
                                    .clickable {
                                        onSelectTradeAsset(index)
                                        onTabChange("trading")
                                    }
                                    .padding(horizontal = 11.dp, vertical = 10.dp)
                                    .testTag("market_row_${asset.ticker.lowercase()}"),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(10.dp)
                            ) {
                                Text(
                                    text = "0${index + 1}",
                                    color = CrownColors.TextSubtle,
                                    fontSize = 10.sp,
                                    fontFamily = FontFamily.Monospace,
                                    modifier = Modifier.width(18.dp)
                                )
                                AssetIconBadge(asset = asset)
                                Column(modifier = Modifier.weight(1f)) {
                                    Text(
                                        text = asset.name,
                                        color = CrownColors.TextPrimary,
                                        fontSize = 13.sp,
                                        fontWeight = FontWeight.Medium
                                    )
                                    Spacer(modifier = Modifier.height(3.dp))
                                    Text(
                                        text = "${asset.ticker} · ${asset.amount}",
                                        color = CrownColors.TextMuted,
                                        fontSize = 10.sp,
                                        fontFamily = FontFamily.Monospace
                                    )
                                }
                                AssetMiniSparkline(
                                    asset = asset,
                                    modifier = Modifier
                                        .width(42.dp)
                                        .height(20.dp)
                                )
                                Column(horizontalAlignment = Alignment.End) {
                                    Text(
                                        text = asset.value,
                                        color = CrownColors.TextPrimary,
                                        fontSize = 13.sp,
                                        fontWeight = FontWeight.Medium,
                                        fontFamily = FontFamily.Monospace
                                    )
                                    Spacer(modifier = Modifier.height(3.dp))
                                    Text(
                                        text = asset.change,
                                        color = CrownColors.PositiveMint,
                                        fontSize = 10.sp,
                                        fontFamily = FontFamily.Monospace
                                    )
                                }
                                Box(
                                    modifier = Modifier
                                        .size(34.dp)
                                        .clip(RoundedCornerShape(9.dp))
                                        .background(Color.White.copy(alpha = 0.04f))
                                        .clickable { onAddWatchlist(asset) }
                                        .testTag("watchlist_button_${asset.ticker.lowercase()}"),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        imageVector = if (uiState.watchlist.contains(asset.ticker)) Icons.Default.Check else Icons.Default.Add,
                                        contentDescription = null,
                                        tint = if (uiState.watchlist.contains(asset.ticker)) CrownColors.NeonCyan else CrownColors.TextMuted,
                                        modifier = Modifier.size(16.dp)
                                    )
                                }
                            }
                        }
                    }
                }
            } else {
                // Active Trading Tab
                Column(modifier = Modifier.fillMaxWidth()) {
                    // Token Pair Selector Bar
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .horizontalScroll(rememberScrollState())
                            .padding(bottom = 14.dp),
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        uiState.assets.forEachIndexed { index, asset ->
                            val active = index == uiState.selectedTradeAssetIndex
                            Row(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(20.dp))
                                    .background(if (active) Color(0x1F53EBD3) else Color.White.copy(alpha = 0.04f))
                                    .border(
                                        1.dp,
                                        if (active) Color(0x4D53EBD3) else Color.White.copy(alpha = 0.06f),
                                        RoundedCornerShape(20.dp)
                                    )
                                    .clickable { onSelectTradeAsset(index) }
                                    .padding(horizontal = 12.dp, vertical = 7.dp)
                                    .testTag("trade_chip_${asset.ticker.lowercase()}"),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(6.dp)
                            ) {
                                AssetIconBadge(asset = asset, boxSize = 22.dp, iconSize = 13.dp)
                                Text(
                                    text = asset.ticker,
                                    color = if (active) CrownColors.NeonCyan else Color(0xFF8B91AC),
                                    fontSize = 11.sp,
                                    fontFamily = FontFamily.Monospace,
                                    fontWeight = FontWeight.Medium
                                )
                            }
                        }
                    }

                    // Active Trading Pair Metadata + Interactive Price History Chart Card
                    var scrubbedPrice by remember(selectedAsset.ticker, uiState.chartTimeframe) { mutableStateOf<String?>(null) }
                    var scrubbedTime by remember(selectedAsset.ticker, uiState.chartTimeframe) { mutableStateOf<String?>(null) }
                    var scrubbedDelta by remember(selectedAsset.ticker, uiState.chartTimeframe) { mutableStateOf<String?>(null) }

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 14.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .background(Color.White.copy(alpha = 0.04f))
                            .border(1.dp, Color.White.copy(alpha = 0.08f), RoundedCornerShape(16.dp))
                            .padding(18.dp)
                            .testTag("active_trading_pair_card")
                    ) {
                        // Top Row: Token Identity (Name, Symbol, Active Trading Pair) + Wallet Balance Metadata
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 12.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(10.dp)
                            ) {
                                AssetIconBadge(asset = selectedAsset, boxSize = 40.dp, iconSize = 20.dp)
                                Column {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                                    ) {
                                        Text(
                                            text = selectedAsset.name,
                                            color = CrownColors.TextPrimary,
                                            fontSize = 15.sp,
                                            fontWeight = FontWeight.SemiBold,
                                            modifier = Modifier.testTag("active_pair_name")
                                        )
                                        Box(
                                            modifier = Modifier
                                                .clip(RoundedCornerShape(5.dp))
                                                .background(Color(0x1F53EBD3))
                                                .border(1.dp, Color(0x4053EBD3), RoundedCornerShape(5.dp))
                                                .padding(horizontal = 6.dp, vertical = 2.dp)
                                        ) {
                                            Text(
                                                text = selectedAsset.pairSymbol,
                                                color = CrownColors.NeonCyan,
                                                fontSize = 9.sp,
                                                fontFamily = FontFamily.Monospace,
                                                fontWeight = FontWeight.SemiBold,
                                                modifier = Modifier.testTag("active_pair_symbol")
                                            )
                                        }
                                    }
                                    Spacer(modifier = Modifier.height(3.dp))
                                    Text(
                                        text = "${t.swap.balance}: ${selectedAsset.amount} (${selectedAsset.balanceUsd})",
                                        color = CrownColors.TextMuted,
                                        fontSize = 10.sp,
                                        fontFamily = FontFamily.Monospace,
                                        modifier = Modifier.testTag("active_pair_balance")
                                    )
                                }
                            }
                        }

                        // Current Price + 24h Change Row
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 12.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.Bottom
                        ) {
                            Row(
                                verticalAlignment = Alignment.Bottom,
                                horizontalArrangement = Arrangement.spacedBy(10.dp)
                            ) {
                                Text(
                                    text = scrubbedPrice ?: selectedAsset.value,
                                    color = CrownColors.TextPrimary,
                                    fontSize = 24.sp,
                                    fontFamily = FontFamily.Monospace,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.testTag("active_pair_price")
                                )
                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(6.dp))
                                        .background(Color(0x1F4BE3B5))
                                        .padding(horizontal = 7.dp, vertical = 3.dp)
                                ) {
                                    Text(
                                        text = "${scrubbedDelta ?: selectedAsset.change} (24h)",
                                        color = CrownColors.PositiveMint,
                                        fontSize = 10.sp,
                                        fontFamily = FontFamily.Monospace,
                                        fontWeight = FontWeight.SemiBold,
                                        modifier = Modifier.testTag("active_pair_change")
                                    )
                                }
                            }

                            scrubbedTime?.let { timeStr ->
                                Text(
                                    text = timeStr,
                                    color = CrownColors.NeonCyan,
                                    fontSize = 10.sp,
                                    fontFamily = FontFamily.Monospace
                                )
                            }
                        }

                        // 24h High / Low / Volume Metadata Strip
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 12.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .background(Color.Black.copy(alpha = 0.20f))
                                .padding(horizontal = 12.dp, vertical = 8.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            PairStatMiniItem(label = "24H HIGH", value = selectedAsset.high24h)
                            PairStatMiniItem(label = "24H LOW", value = selectedAsset.low24h)
                            PairStatMiniItem(label = "24H VOL", value = selectedAsset.volume24h)
                            PairStatMiniItem(label = "MCAP", value = selectedAsset.marketCap)
                        }

                        // Interactive Price History Chart (Recharts Area / TradingView Candles)
                        InteractiveTokenPriceChart(
                            asset = selectedAsset,
                            timeframe = uiState.chartTimeframe,
                            chartStyle = uiState.chartStyle,
                            onInspectPoint = { p, tm, d ->
                                scrubbedPrice = p
                                scrubbedTime = tm
                                scrubbedDelta = d
                            },
                            modifier = Modifier.fillMaxWidth()
                        )

                        // Timeframe Selector + Chart Mode Toggle (Area vs Candles)
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 12.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                                listOf("1H", "1D", "1W", "1M", "1Y").forEach { tf ->
                                    val isTfActive = uiState.chartTimeframe == tf
                                    Box(
                                        modifier = Modifier
                                            .clip(RoundedCornerShape(6.dp))
                                            .background(
                                                if (isTfActive) Color(0x2653EBD3)
                                                else Color.White.copy(alpha = 0.04f)
                                            )
                                            .border(
                                                1.dp,
                                                if (isTfActive) Color(0x5953EBD3) else Color.Transparent,
                                                RoundedCornerShape(6.dp)
                                            )
                                            .clickable { onTimeframeChange(tf) }
                                            .padding(horizontal = 9.dp, vertical = 5.dp)
                                            .testTag("chart_tf_${tf.lowercase()}")
                                    ) {
                                        Text(
                                            text = tf,
                                            color = if (isTfActive) CrownColors.NeonCyan else CrownColors.TextSubtle,
                                            fontSize = 9.sp,
                                            fontFamily = FontFamily.Monospace,
                                            fontWeight = if (isTfActive) FontWeight.Bold else FontWeight.Normal
                                        )
                                    }
                                }
                            }

                            // Area / Candles Mode Toggle
                            Row(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(7.dp))
                                    .background(Color.White.copy(alpha = 0.04f))
                                    .border(1.dp, CrownColors.SurfaceBorder, RoundedCornerShape(7.dp))
                                    .padding(2.dp),
                                horizontalArrangement = Arrangement.spacedBy(2.dp)
                            ) {
                                ChartStyleChip(
                                    label = "LINE",
                                    active = uiState.chartStyle == "area",
                                    tag = "chart_style_area",
                                    onClick = { onChartStyleChange("area") }
                                )
                                ChartStyleChip(
                                    label = "CANDLES",
                                    active = uiState.chartStyle == "candles",
                                    tag = "chart_style_candles",
                                    onClick = { onChartStyleChange("candles") }
                                )
                            }
                        }
                    }

                    // Quick-Action Buttons Row: Buy · Sell · Swap
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 14.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        val isBuy = uiState.tradeSide == "buy"
                        // BUY Quick Action Button
                        Row(
                            modifier = Modifier
                                .weight(1f)
                                .height(48.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .background(
                                    if (isBuy) Brush.horizontalGradient(
                                        listOf(Color(0x3853EBD3), Color(0x2238B6FF))
                                    )
                                    else Brush.horizontalGradient(
                                        listOf(Color.White.copy(alpha = 0.045f), Color.White.copy(alpha = 0.045f))
                                    )
                                )
                                .border(
                                    1.dp,
                                    if (isBuy) Color(0x8053EBD3) else Color.White.copy(alpha = 0.08f),
                                    RoundedCornerShape(12.dp)
                                )
                                .clickable {
                                    onTradeSideChange("buy")
                                    onQuickAction("buy")
                                }
                                .testTag("quick_action_buy"),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Default.NorthEast,
                                contentDescription = null,
                                tint = if (isBuy) CrownColors.NeonCyan else CrownColors.TextSecondary,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = t.markets.buy,
                                color = if (isBuy) CrownColors.NeonCyan else CrownColors.TextSecondary,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }

                        // SELL Quick Action Button
                        Row(
                            modifier = Modifier
                                .weight(1f)
                                .height(48.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .background(
                                    if (!isBuy) Brush.horizontalGradient(
                                        listOf(Color(0x38FF6B6B), Color(0x24FF9E7B))
                                    )
                                    else Brush.horizontalGradient(
                                        listOf(Color.White.copy(alpha = 0.045f), Color.White.copy(alpha = 0.045f))
                                    )
                                )
                                .border(
                                    1.dp,
                                    if (!isBuy) Color(0x80FF6B6B) else Color.White.copy(alpha = 0.08f),
                                    RoundedCornerShape(12.dp)
                                )
                                .clickable {
                                    onTradeSideChange("sell")
                                    onQuickAction("sell")
                                }
                                .testTag("quick_action_sell"),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Default.SouthWest,
                                contentDescription = null,
                                tint = if (!isBuy) CrownColors.CoralAlert else CrownColors.TextSecondary,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = t.markets.sell,
                                color = if (!isBuy) CrownColors.CoralAlert else CrownColors.TextSecondary,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }

                        // SWAP Quick Action Button
                        Row(
                            modifier = Modifier
                                .weight(1f)
                                .height(48.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .background(
                                    Brush.horizontalGradient(
                                        listOf(Color(0x289A7BFF), Color(0x1F56F2DF))
                                    )
                                )
                                .border(
                                    1.dp,
                                    Color(0x599A7BFF),
                                    RoundedCornerShape(12.dp)
                                )
                                .clickable { onQuickAction("swap") }
                                .testTag("quick_action_swap"),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.CompareArrows,
                                contentDescription = null,
                                tint = CrownColors.AccentViolet,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = t.nav.swap,
                                color = CrownColors.TextPrimary,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }

                    // Trade Order Ticket Form
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 14.dp)
                            .clip(RoundedCornerShape(14.dp))
                            .background(Color.White.copy(alpha = 0.04f))
                            .border(1.dp, Color.White.copy(alpha = 0.07f), RoundedCornerShape(14.dp))
                            .padding(16.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 10.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "${t.markets.orderAmount} (${selectedAsset.ticker})",
                                color = CrownColors.TextMuted,
                                fontSize = 10.sp,
                                fontFamily = FontFamily.Monospace
                            )
                            Box(
                                modifier = Modifier
                                    .width(136.dp)
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(Color.Black.copy(alpha = 0.25f))
                                    .border(1.dp, Color.White.copy(alpha = 0.08f), RoundedCornerShape(8.dp))
                                    .padding(horizontal = 10.dp, vertical = 8.dp)
                            ) {
                                BasicTextField(
                                    value = uiState.orderAmount,
                                    onValueChange = onOrderAmountChange,
                                    singleLine = true,
                                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                                    textStyle = TextStyle(
                                        color = CrownColors.TextPrimary,
                                        fontSize = 12.sp,
                                        fontFamily = FontFamily.Monospace,
                                        textAlign = TextAlign.End
                                    ),
                                    cursorBrush = SolidColor(CrownColors.NeonCyan),
                                    decorationBox = { innerTextField ->
                                        Box(
                                            modifier = Modifier.fillMaxWidth(),
                                            contentAlignment = Alignment.CenterEnd
                                        ) {
                                            if (uiState.orderAmount.isEmpty()) {
                                                Text(
                                                    text = "0.00",
                                                    color = CrownColors.TextSubtle,
                                                    fontSize = 12.sp,
                                                    fontFamily = FontFamily.Monospace
                                                )
                                            }
                                            innerTextField()
                                        }
                                    },
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .testTag("trade_amount_input")
                                )
                            }
                        }

                        // Quick Balance Percentage Allocation Chips
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 10.dp),
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            listOf(
                                "25%" to 0.25,
                                "50%" to 0.50,
                                "75%" to 0.75,
                                "MAX" to 1.00
                            ).forEach { (pctLabel, ratio) ->
                                Box(
                                    modifier = Modifier
                                        .weight(1f)
                                        .clip(RoundedCornerShape(6.dp))
                                        .background(Color.White.copy(alpha = 0.04f))
                                        .border(1.dp, Color.White.copy(alpha = 0.06f), RoundedCornerShape(6.dp))
                                        .clickable { onOrderPercentSelect(ratio) }
                                        .padding(vertical = 5.dp)
                                        .testTag("order_pct_${pctLabel.lowercase()}"),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = pctLabel,
                                        color = CrownColors.TextSecondary,
                                        fontSize = 9.sp,
                                        fontFamily = FontFamily.Monospace
                                    )
                                }
                            }
                        }

                        HorizontalDivider(color = Color.White.copy(alpha = 0.05f), thickness = 1.dp)

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 10.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = t.markets.orderType,
                                color = CrownColors.TextMuted,
                                fontSize = 10.sp,
                                fontFamily = FontFamily.Monospace
                            )
                            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                                OrderTypeChip(
                                    label = t.markets.marketOrder,
                                    active = uiState.orderType == "market",
                                    tag = "order_type_market",
                                    onClick = { onOrderTypeChange("market") }
                                )
                                OrderTypeChip(
                                    label = t.markets.limitOrder,
                                    active = uiState.orderType == "limit",
                                    tag = "order_type_limit",
                                    onClick = { onOrderTypeChange("limit") }
                                )
                            }
                        }

                        HorizontalDivider(color = Color.White.copy(alpha = 0.05f), thickness = 1.dp)

                        val parsedAmt = uiState.orderAmount.toDoubleOrNull() ?: 0.0
                        val estUsd = parsedAmt * selectedAsset.unitPrice

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 10.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = t.swap.balance,
                                color = CrownColors.TextMuted,
                                fontSize = 10.sp,
                                fontFamily = FontFamily.Monospace
                            )
                            Text(
                                text = if (parsedAmt > 0.0) {
                                    "≈ ${selectedAsset.formatPrice(estUsd.toFloat())} · ${selectedAsset.amount}"
                                } else {
                                    "${selectedAsset.amount} (${selectedAsset.balanceUsd})"
                                },
                                color = CrownColors.TextSecondary,
                                fontSize = 10.sp,
                                fontFamily = FontFamily.Monospace
                            )
                        }
                    }

                    val isSell = uiState.tradeSide == "sell"
                    CrownPrimaryButton(
                        text = "${if (isSell) t.markets.sell else t.markets.buy} ${selectedAsset.ticker}",
                        tag = "submit_trade_order_button",
                        isSellStyle = isSell,
                        onClick = onSubmitTrade
                    )
                }
            }
        }
    }
}

@Composable
private fun PairStatMiniItem(label: String, value: String) {
    Column(horizontalAlignment = Alignment.Start) {
        Text(
            text = label,
            color = CrownColors.TextSubtle,
            fontSize = 8.sp,
            fontFamily = FontFamily.Monospace
        )
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = value,
            color = CrownColors.TextSecondary,
            fontSize = 10.sp,
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
private fun ChartStyleChip(
    label: String,
    active: Boolean,
    tag: String,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(5.dp))
            .background(if (active) Color(0x2653EBD3) else Color.Transparent)
            .clickable(onClick = onClick)
            .padding(horizontal = 8.dp, vertical = 4.dp)
            .testTag(tag)
    ) {
        Text(
            text = label,
            color = if (active) CrownColors.NeonCyan else CrownColors.TextSubtle,
            fontSize = 8.sp,
            fontFamily = FontFamily.Monospace,
            fontWeight = if (active) FontWeight.Bold else FontWeight.Normal
        )
    }
}

@Composable
private fun MarketTabButton(
    modifier: Modifier = Modifier,
    text: String,
    icon: ImageVector,
    active: Boolean,
    tag: String,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier
            .height(40.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(
                if (active) Brush.horizontalGradient(
                    listOf(Color(0x2638E0D1), Color(0x267B4EE4))
                )
                else Brush.horizontalGradient(listOf(Color.Transparent, Color.Transparent))
            )
            .then(
                if (active) Modifier.border(1.dp, Color(0x4053EBD3), RoundedCornerShape(10.dp))
                else Modifier
            )
            .clickable(onClick = onClick)
            .testTag(tag),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = if (active) CrownColors.NeonCyan else Color(0xFF7A82A0),
            modifier = Modifier.size(15.dp)
        )
        Spacer(modifier = Modifier.width(6.dp))
        Text(
            text = text,
            color = if (active) CrownColors.NeonCyan else Color(0xFF7A82A0),
            fontSize = 11.sp,
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
private fun OrderTypeChip(
    label: String,
    active: Boolean,
    tag: String,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(7.dp))
            .background(if (active) Color(0x1F53EBD3) else Color.White.copy(alpha = 0.04f))
            .clickable(onClick = onClick)
            .padding(horizontal = 12.dp, vertical = 6.dp)
            .testTag(tag)
    ) {
        Text(
            text = label,
            color = if (active) CrownColors.NeonCyan else Color(0xFF8B91AC),
            fontSize = 10.sp,
            fontFamily = FontFamily.Monospace
        )
    }
}

@Composable
fun StakingScreen(
    uiState: CrownVaultUiState,
    onBack: () -> Unit,
    onStake: () -> Unit
) {
    BackHandler(onBack = onBack)
    val t = uiState.t
    Column(modifier = Modifier.fillMaxWidth()) {
        PageIntroSection(
            kicker = t.staking.kicker,
            title = t.staking.title,
            subtitle = t.staking.subtitle
        )

        // Stake Hero
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(18.dp))
                .background(
                    Brush.linearGradient(
                        colors = listOf(Color(0x1F42E2C7), Color(0x219464FF))
                    )
                )
                .border(1.dp, Color(0x3358E6D2), RoundedCornerShape(18.dp))
                .padding(22.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(17.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(58.dp)
                    .clip(CircleShape)
                    .background(
                        Brush.radialGradient(
                            colors = listOf(Color(0x42FFAE6C), Color(0x08FF7E66))
                        )
                    )
                    .border(1.dp, Color(0x6BFFAE6C), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Outlined.LocalFireDepartment,
                    contentDescription = null,
                    tint = Color(0xFFFFB477),
                    modifier = Modifier.size(28.dp)
                )
            }

            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(
                    text = t.staking.totalStaked,
                    color = CrownColors.TextMuted,
                    fontSize = 11.sp
                )
                Text(
                    text = buildAnnotatedString {
                        withStyle(SpanStyle(color = CrownColors.TextPrimary, fontSize = 25.sp, fontWeight = FontWeight.Medium)) {
                            append(if (uiState.staked) "1,200.00 " else "0.00 ")
                        }
                        withStyle(SpanStyle(color = CrownColors.NeonCyan, fontSize = 11.sp, fontFamily = FontFamily.Monospace)) {
                            append("CRWN")
                        }
                    },
                    modifier = Modifier.testTag("staked_amount_text")
                )
                Text(
                    text = if (uiState.staked) t.staking.earnedThisWeek else t.staking.startEarning,
                    color = CrownColors.PositiveMint,
                    fontSize = 10.sp,
                    fontFamily = FontFamily.Monospace
                )
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // 3-Column Stake Grid
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(13.dp))
                .background(Color.White.copy(alpha = 0.028f))
                .padding(vertical = 15.dp)
        ) {
            StakeStatCell(
                modifier = Modifier.weight(1f),
                label = t.staking.currentApy,
                value = "14.20%",
                showDivider = true
            )
            StakeStatCell(
                modifier = Modifier.weight(1f),
                label = t.staking.lockPeriod,
                value = t.staking.flexible,
                showDivider = true
            )
            StakeStatCell(
                modifier = Modifier.weight(1f),
                label = t.staking.networkFee,
                value = "0.01%",
                showDivider = false
            )
        }

        Spacer(modifier = Modifier.height(14.dp))

        CrownPrimaryButton(
            text = if (uiState.staked) t.staking.stakingActive else t.staking.stakeCrwn,
            icon = if (uiState.staked) Icons.Default.Check else Icons.Outlined.Lock,
            tag = "stake_crwn_button",
            onClick = onStake
        )

        Spacer(modifier = Modifier.height(18.dp))

        InfoBannerPanel(
            title = t.staking.protectedBy,
            detail = t.staking.protectedDetail
        )
    }
}

@Composable
private fun StakeStatCell(
    modifier: Modifier = Modifier,
    label: String,
    value: String,
    showDivider: Boolean
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 13.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Text(
                text = label,
                color = CrownColors.TextMuted,
                fontSize = 9.sp,
                fontFamily = FontFamily.Monospace
            )
            Text(
                text = value,
                color = CrownColors.TextPrimary,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium
            )
        }
        if (showDivider) {
            Box(
                modifier = Modifier
                    .width(1.dp)
                    .height(32.dp)
                    .background(Color.White.copy(alpha = 0.08f))
            )
        }
    }
}

@Composable
fun SwapScreen(
    uiState: CrownVaultUiState,
    onBack: () -> Unit,
    onSwap: () -> Unit,
    onCycleFromAsset: () -> Unit = {},
    onCycleToAsset: () -> Unit = {}
) {
    BackHandler(onBack = onBack)
    val t = uiState.t
    val fromAsset = uiState.assets.getOrElse(uiState.swapFromAssetIndex) { uiState.assets.first() }
    val toAsset = uiState.assets.getOrElse(uiState.swapToAssetIndex) { uiState.assets.getOrElse(2) { uiState.assets.first() } }
    val fromTicker = fromAsset.ticker
    val toTicker = toAsset.ticker

    val inputAmt = uiState.swapAmountInput.toDoubleOrNull() ?: 1.0
    val conversionRate = if (toAsset.unitPrice > 0.0) fromAsset.unitPrice / toAsset.unitPrice else 1.0
    val outputAmt = inputAmt * conversionRate
    val formattedOutput = when {
        outputAmt >= 100.0 -> "%,.2f".format(java.util.Locale.US, outputAmt)
        outputAmt >= 1.0 -> "%.4f".format(java.util.Locale.US, outputAmt)
        else -> "%.6f".format(java.util.Locale.US, outputAmt)
    }
    val formattedRate = when {
        conversionRate >= 100.0 -> "%,.2f".format(java.util.Locale.US, conversionRate)
        conversionRate >= 1.0 -> "%.4f".format(java.util.Locale.US, conversionRate)
        else -> "%.6f".format(java.util.Locale.US, conversionRate)
    }

    Column(modifier = Modifier.fillMaxWidth()) {
        PageIntroSection(
            kicker = t.swap.kicker,
            title = t.swap.title,
            subtitle = t.swap.subtitle
        )

        // Swap Card with overlapping center toggle
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(18.dp))
                .background(Color.White.copy(alpha = 0.045f))
                .border(1.dp, Color.White.copy(alpha = 0.09f), RoundedCornerShape(18.dp))
                .padding(19.dp)
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                // You Pay
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = t.swap.youPay,
                        color = CrownColors.TextMuted,
                        fontSize = 9.sp,
                        fontFamily = FontFamily.Monospace
                    )
                    Text(
                        text = "${t.swap.balance}: ${fromAsset.amount}",
                        color = CrownColors.TextMuted,
                        fontSize = 9.sp,
                        fontFamily = FontFamily.Monospace
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                SwapAssetBox(
                    asset = fromAsset,
                    ticker = fromTicker,
                    amount = uiState.swapAmountInput,
                    tag = "swap_from_asset_box",
                    onClick = onCycleFromAsset
                )

                // Center Swap Toggle Row
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Box(
                        modifier = Modifier
                            .size(42.dp)
                            .clip(CircleShape)
                            .background(Color(0xFF5EEEDC))
                            .border(4.dp, Color(0xFF141527), CircleShape)
                            .clickable(onClick = onSwap)
                            .testTag("swap_direction_toggle"),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.CompareArrows,
                            contentDescription = stringResource(R.string.content_desc_swap_assets),
                            tint = Color(0xFF071115),
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }

                // You Receive
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = t.swap.youReceive,
                        color = CrownColors.TextMuted,
                        fontSize = 9.sp,
                        fontFamily = FontFamily.Monospace
                    )
                    Text(
                        text = "${t.swap.balance}: ${toAsset.amount}",
                        color = CrownColors.TextMuted,
                        fontSize = 9.sp,
                        fontFamily = FontFamily.Monospace
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                SwapAssetBox(
                    asset = toAsset,
                    ticker = toTicker,
                    amount = formattedOutput,
                    tag = "swap_to_asset_box",
                    onClick = onCycleToAsset
                )

                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "1 $fromTicker = $formattedRate $toTicker",
                        color = CrownColors.TextMuted,
                        fontSize = 9.sp,
                        fontFamily = FontFamily.Monospace
                    )
                    Text(
                        text = t.swap.bestRoute,
                        color = CrownColors.PositiveMint,
                        fontSize = 9.sp,
                        fontFamily = FontFamily.Monospace
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        CrownPrimaryButton(
            text = "${t.swap.reviewSwap} ($fromTicker → $toTicker)",
            icon = Icons.Outlined.Bolt,
            tag = "review_swap_button",
            onClick = onSwap
        )

        Spacer(modifier = Modifier.height(14.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "${t.swap.poweredBy} ",
                color = CrownColors.TextSubtle,
                fontSize = 9.sp,
                fontFamily = FontFamily.Monospace
            )
            CrownBrandIcon(size = 13.dp, tint = Color(0xFF9198AE))
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = t.swap.router,
                color = Color(0xFF9198AE),
                fontSize = 9.sp,
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Composable
private fun SwapAssetBox(
    asset: Asset,
    ticker: String,
    amount: String,
    tag: String = "swap_asset_box",
    onClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(58.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(Color.Black.copy(alpha = 0.25f))
            .clickable(onClick = onClick)
            .padding(horizontal = 12.dp)
            .testTag(tag),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            AssetIconBadge(asset = asset, boxSize = 29.dp, iconSize = 15.dp)
            Column {
                Text(
                    text = ticker,
                    color = CrownColors.TextPrimary,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    text = asset.name,
                    color = CrownColors.TextMuted,
                    fontSize = 9.sp
                )
            }
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = null,
                tint = CrownColors.TextMuted,
                modifier = Modifier.size(16.dp)
            )
        }
        Text(
            text = amount,
            color = CrownColors.TextPrimary,
            fontSize = 17.sp,
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun CardScreen(
    uiState: CrownVaultUiState,
    onBack: () -> Unit,
    onToggleLock: () -> Unit,
    onToggleDetails: () -> Unit = {},
    onNotify: (String) -> Unit,
    onViewAllActivity: () -> Unit = {}
) {
    BackHandler(onBack = onBack)
    val t = uiState.t
    val context = LocalContext.current
    val revealed = uiState.cardDetailsRevealed
    val cardAlpha by animateFloatAsState(
        targetValue = if (uiState.cardLocked) 0.42f else 1f,
        animationSpec = tween(350),
        label = "cardAlpha"
    )

    Column(modifier = Modifier.fillMaxWidth()) {
        PageIntroSection(
            kicker = t.card.kicker,
            title = t.card.title,
            subtitle = t.card.subtitle
        )

        // Quantum Card Visual
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(222.dp)
                .rotate(-1f)
                .alpha(cardAlpha)
                .clip(RoundedCornerShape(19.dp))
                .background(
                    Brush.linearGradient(
                        colors = listOf(
                            Color(0xFF11172C),
                            Color(0xFF292054),
                            Color(0xFF1F6A7A),
                            Color(0xFF3A1D6E)
                        )
                    )
                )
                .border(
                    1.dp,
                    if (revealed) CrownColors.NeonCyan.copy(alpha = 0.55f) else Color(0x61B19EFF),
                    RoundedCornerShape(19.dp)
                )
                .padding(22.dp)
                .testTag("quantum_virtual_card"),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    CrownBrandIcon(size = 15.dp, tint = CrownColors.NeonCyan)
                    Text(
                        text = "CROWN",
                        color = CrownColors.TextPrimary,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.2.sp
                    )
                }
                Text(
                    text = "∞ / ∞",
                    color = CrownColors.TextSecondary,
                    fontSize = 11.sp,
                    fontFamily = FontFamily.Monospace
                )
            }

            // Metallic Chip
            Box(
                modifier = Modifier
                    .width(38.dp)
                    .height(28.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .background(
                        Brush.linearGradient(
                            colors = listOf(Color(0xFFFFD59A), Color(0xFFA86E52))
                        )
                    )
                    .border(1.dp, Color(0xCCFFD49F), RoundedCornerShape(6.dp))
            )

            // Card Number Row with individual Copy icon when revealed
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = if (revealed) "4892 0019 8841 2389" else "••••   ••••   ••••   2389",
                    color = Color(0xFFF6F7FF),
                    fontSize = 16.sp,
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.Medium,
                    letterSpacing = 1.2.sp,
                    modifier = Modifier.testTag("card_number_text")
                )

                if (revealed) {
                    Box(
                        modifier = Modifier
                            .size(28.dp)
                            .clip(RoundedCornerShape(7.dp))
                            .background(Color.White.copy(alpha = 0.12f))
                            .border(1.dp, CrownColors.NeonCyan.copy(alpha = 0.45f), RoundedCornerShape(7.dp))
                            .clickable {
                                copyToClipboard(context, "4892 0019 8841 2389")
                                onNotify("Card number copied!")
                            }
                            .testTag("copy_card_number_button"),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.ContentCopy,
                            contentDescription = "Copy Card Number",
                            tint = CrownColors.NeonCyan,
                            modifier = Modifier.size(14.dp)
                        )
                    }
                }
            }

            // Card Footer: Holder Name / Expiry + Copy / CVV + Copy / Network Emblem
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "AEON VAULT",
                    color = Color(0xFFD0D5EC),
                    fontSize = 10.sp,
                    fontFamily = FontFamily.Monospace,
                    letterSpacing = 0.8.sp
                )

                if (revealed) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        // Expiry Date + Individual Copy Button
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Text(
                                text = "Expiry: 08/29",
                                color = Color(0xFFF6F7FF),
                                fontSize = 10.sp,
                                fontFamily = FontFamily.Monospace,
                                fontWeight = FontWeight.Medium,
                                modifier = Modifier.testTag("card_expiry_text")
                            )
                            Box(
                                modifier = Modifier
                                    .size(24.dp)
                                    .clip(RoundedCornerShape(6.dp))
                                    .background(Color.White.copy(alpha = 0.12f))
                                    .border(1.dp, CrownColors.NeonCyan.copy(alpha = 0.45f), RoundedCornerShape(6.dp))
                                    .clickable {
                                        copyToClipboard(context, "08/29")
                                        onNotify("Expiry date copied!")
                                    }
                                    .testTag("copy_card_expiry_button"),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Outlined.ContentCopy,
                                    contentDescription = "Copy Expiry Date",
                                    tint = CrownColors.NeonCyan,
                                    modifier = Modifier.size(12.dp)
                                )
                            }
                        }

                        // CVV + Individual Copy Button
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Text(
                                text = "CVV: 888",
                                color = Color(0xFFF6F7FF),
                                fontSize = 10.sp,
                                fontFamily = FontFamily.Monospace,
                                fontWeight = FontWeight.Medium,
                                modifier = Modifier.testTag("card_cvv_text")
                            )
                            Box(
                                modifier = Modifier
                                    .size(24.dp)
                                    .clip(RoundedCornerShape(6.dp))
                                    .background(Color.White.copy(alpha = 0.12f))
                                    .border(1.dp, CrownColors.NeonCyan.copy(alpha = 0.45f), RoundedCornerShape(6.dp))
                                    .clickable {
                                        copyToClipboard(context, "888")
                                        onNotify("CVV copied!")
                                    }
                                    .testTag("copy_card_cvv_button"),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Outlined.ContentCopy,
                                    contentDescription = "Copy CVV",
                                    tint = CrownColors.NeonCyan,
                                    modifier = Modifier.size(12.dp)
                                )
                            }
                        }
                    }
                } else {
                    Text(
                        text = "••/••  ·  CVV •••",
                        color = Color(0xFFB5BBE3),
                        fontSize = 10.sp,
                        fontFamily = FontFamily.Monospace
                    )
                }

                Text(
                    text = "◉◉",
                    color = Color(0xFFFFBB7C),
                    fontSize = 18.sp,
                    letterSpacing = (-4).sp
                )
            }
        }

        // Card status
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 15.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(7.dp)
                    .clip(CircleShape)
                    .background(if (uiState.cardLocked) CrownColors.CoralAlert else CrownColors.NeonCyan)
            )
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = t.card.cardReady,
                    color = CrownColors.TextPrimary,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    text = t.card.virtualCard,
                    color = CrownColors.TextMuted,
                    fontSize = 10.sp,
                    fontFamily = FontFamily.Monospace
                )
            }
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = null,
                tint = CrownColors.TextMuted,
                modifier = Modifier.size(18.dp)
            )
        }

        // Card actions: Details (Eye / EyeOff toggle) & Lock Card
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 26.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                modifier = Modifier
                    .weight(1f)
                    .height(48.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(
                        if (revealed) Color(0x1F53EBD3) else CrownColors.SurfaceGlass
                    )
                    .border(
                        1.dp,
                        if (revealed) CrownColors.NeonCyan.copy(alpha = 0.5f) else CrownColors.SurfaceBorder,
                        RoundedCornerShape(10.dp)
                    )
                    .clickable(onClick = onToggleDetails)
                    .testTag("card_details_button"),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = if (revealed) Icons.Outlined.VisibilityOff else Icons.Outlined.Visibility,
                    contentDescription = t.card.details,
                    tint = if (revealed) CrownColors.NeonCyan else CrownColors.TextSecondary,
                    modifier = Modifier.size(17.dp)
                )
                Spacer(modifier = Modifier.width(7.dp))
                Text(
                    text = t.card.details,
                    color = if (revealed) CrownColors.NeonCyan else CrownColors.TextSecondary,
                    fontSize = 12.sp,
                    fontWeight = if (revealed) FontWeight.SemiBold else FontWeight.Normal
                )
            }

            Row(
                modifier = Modifier
                    .weight(1f)
                    .height(48.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(
                        if (uiState.cardLocked) Color(0x22FF8B8B) else CrownColors.SurfaceGlass
                    )
                    .border(
                        1.dp,
                        if (uiState.cardLocked) CrownColors.CoralAlert.copy(alpha = 0.5f) else CrownColors.SurfaceBorder,
                        RoundedCornerShape(10.dp)
                    )
                    .clickable(onClick = onToggleLock)
                    .testTag("card_lock_toggle_button"),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Outlined.Lock,
                    contentDescription = null,
                    tint = if (uiState.cardLocked) CrownColors.CoralAlert else CrownColors.TextSecondary,
                    modifier = Modifier.size(17.dp)
                )
                Spacer(modifier = Modifier.width(7.dp))
                Text(
                    text = if (uiState.cardLocked) t.card.unlockCard else t.card.lockCard,
                    color = if (uiState.cardLocked) CrownColors.CoralAlert else CrownColors.TextSecondary,
                    fontSize = 12.sp,
                    fontWeight = if (uiState.cardLocked) FontWeight.SemiBold else FontWeight.Normal
                )
            }
        }

        // Recent Spend Section
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 14.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom
        ) {
            Column {
                Text(
                    text = t.card.recentSpend,
                    color = CrownColors.TextMuted,
                    fontSize = 9.sp,
                    fontFamily = FontFamily.Monospace,
                    letterSpacing = 1.5.sp
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = t.card.activity,
                    color = CrownColors.TextPrimary,
                    fontSize = 21.sp,
                    fontWeight = FontWeight.Medium
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(3.dp),
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .clickable(onClick = onViewAllActivity)
                    .padding(horizontal = 6.dp, vertical = 4.dp)
                    .testTag("card_see_all_activity_button")
            ) {
                Text(
                    text = t.card.seeAll,
                    color = CrownColors.NeonCyan,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Medium
                )
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                    contentDescription = null,
                    tint = CrownColors.NeonCyan,
                    modifier = Modifier.size(15.dp)
                )
            }
        }

        ActivityRow(
            icon = Icons.Outlined.FileDownload,
            title = t.card.orbitalMarket,
            subtitle = t.card.today,
            amount = "−$42.80",
            isPositive = false
        )
        Spacer(modifier = Modifier.height(8.dp))
        ActivityRow(
            icon = Icons.Outlined.MonetizationOn,
            title = t.card.cashbackReward,
            subtitle = t.card.yesterday,
            amount = "+$4.28",
            isPositive = true
        )
    }
}

@Composable
private fun ActivityRow(
    icon: ImageVector,
    title: String,
    subtitle: String,
    amount: String,
    isPositive: Boolean
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Box(
            modifier = Modifier
                .size(32.dp)
                .clip(RoundedCornerShape(9.dp))
                .background(Color(0x1A53EBD3)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = CrownColors.NeonCyan,
                modifier = Modifier.size(16.dp)
            )
        }
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                color = CrownColors.TextPrimary,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = subtitle,
                color = CrownColors.TextMuted,
                fontSize = 10.sp,
                fontFamily = FontFamily.Monospace
            )
        }
        Text(
            text = amount,
            color = if (isPositive) CrownColors.PositiveMint else CrownColors.TextPrimary,
            fontSize = 12.sp,
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun PlusScreen(
    uiState: CrownVaultUiState,
    onBack: () -> Unit,
    onOpenVaultPlusModal: () -> Unit
) {
    BackHandler(onBack = onBack)
    val t = uiState.t
    Column(modifier = Modifier.fillMaxWidth()) {
        // Plus Banner
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(18.dp))
                .background(
                    Brush.linearGradient(
                        colors = listOf(
                            Color(0x2438E0D1),
                            Color(0x732E255E),
                            Color(0x59B670FF)
                        )
                    )
                )
                .border(1.dp, Color(0x3DAA82FF), RoundedCornerShape(18.dp))
                .padding(22.dp)
        ) {
            Text(
                text = "✦",
                color = Color.White.copy(alpha = 0.14f),
                fontSize = 86.sp,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .offset(x = (-4).dp, y = (-8).dp)
                    .rotate(18f)
            )

            Column(modifier = Modifier.fillMaxWidth()) {
                Box(
                    modifier = Modifier
                        .size(45.dp)
                        .clip(RoundedCornerShape(13.dp))
                        .background(Color(0x26C796FF)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Outlined.AutoAwesome,
                        contentDescription = null,
                        tint = Color(0xFFCBA6FF),
                        modifier = Modifier.size(26.dp)
                    )
                }

                Spacer(modifier = Modifier.height(22.dp))

                Text(
                    text = t.plus.kicker,
                    color = CrownColors.TextMuted,
                    fontSize = 9.sp,
                    fontFamily = FontFamily.Monospace,
                    letterSpacing = 1.5.sp
                )

                Text(
                    text = buildAnnotatedString {
                        withStyle(SpanStyle(color = CrownColors.TextPrimary, fontSize = 34.sp, fontWeight = FontWeight.Medium)) {
                            append(t.plus.title)
                        }
                        withStyle(SpanStyle(color = Color(0xFFBD8CFF), fontSize = 34.sp, fontWeight = FontWeight.Medium)) {
                            append("+")
                        }
                    },
                    modifier = Modifier.padding(vertical = 6.dp)
                )

                Text(
                    text = t.plus.subtitle,
                    color = Color(0xFF9098B5),
                    fontSize = 12.sp,
                    modifier = Modifier.padding(bottom = 12.dp)
                )

                Text(
                    text = t.vaultPlus.freemiumMessage,
                    color = Color(0xFF8B91AC),
                    fontSize = 11.sp,
                    lineHeight = 16.sp,
                    modifier = Modifier.padding(bottom = 18.dp)
                )

                if (uiState.vaultPlusActive) {
                    Row(
                        modifier = Modifier
                            .clip(RoundedCornerShape(9.dp))
                            .background(Color(0x1A53EBD3))
                            .border(1.dp, Color(0x4053EBD3), RoundedCornerShape(9.dp))
                            .padding(horizontal = 14.dp, vertical = 9.dp)
                            .testTag("vault_plus_active_badge"),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.VerifiedUser,
                            contentDescription = null,
                            tint = CrownColors.PositiveMint,
                            modifier = Modifier.size(16.dp)
                        )
                        Text(
                            text = t.vaultPlus.statusActive,
                            color = CrownColors.PositiveMint,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                } else {
                    Row(
                        modifier = Modifier
                            .heightIn(min = 48.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(Color(0xFFE7DBFF))
                            .clickable(onClick = onOpenVaultPlusModal)
                            .padding(horizontal = 16.dp, vertical = 10.dp)
                            .testTag("start_free_trial_button"),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(7.dp)
                    ) {
                        Text(
                            text = t.plus.startFreeTrial,
                            color = Color(0xFF312451),
                            fontSize = 12.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                        Icon(
                            imageVector = Icons.Default.NorthEast,
                            contentDescription = null,
                            tint = Color(0xFF312451),
                            modifier = Modifier.size(15.dp)
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(18.dp))

        // Benefits
        BenefitRow(
            icon = Icons.Outlined.Bolt,
            title = t.plus.instantSettlement,
            detail = t.plus.instantSettlementDetail
        )
        BenefitRow(
            icon = Icons.Outlined.CardGiftcard,
            title = t.plus.doubleRewards,
            detail = t.plus.doubleRewardsDetail
        )
        BenefitRow(
            icon = Icons.Outlined.Hub,
            title = t.plus.priorityRouting,
            detail = t.plus.priorityRoutingDetail
        )

        Spacer(modifier = Modifier.height(18.dp))

        // Member note
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(13.dp))
                .background(Color.White.copy(alpha = 0.035f))
                .padding(15.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            CrownBrandIcon(size = 20.dp, tint = Color(0xFFA583F5))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = t.plus.curated,
                    color = Color(0xFFDCD5FA),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium
                )
                Spacer(modifier = Modifier.height(3.dp))
                Text(
                    text = t.plus.joinMembers,
                    color = CrownColors.TextMuted,
                    fontSize = 10.sp,
                    fontFamily = FontFamily.Monospace
                )
            }
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = null,
                tint = Color(0xFFA583F5),
                modifier = Modifier.size(17.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        ZeroKnowledgeBadge(text = t.common.zeroKnowledgeBadge)
    }
}

@Composable
private fun BenefitRow(
    icon: ImageVector,
    title: String,
    detail: String
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 56.dp)
                .padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .clip(RoundedCornerShape(9.dp))
                    .background(Color(0x1753EBD3)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = CrownColors.NeonCyan,
                    modifier = Modifier.size(17.dp)
                )
            }
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    color = CrownColors.TextPrimary,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = detail,
                    color = CrownColors.TextMuted,
                    fontSize = 11.sp
                )
            }
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = null,
                tint = CrownColors.NeonCyan,
                modifier = Modifier.size(16.dp)
            )
        }
        HorizontalDivider(color = Color.White.copy(alpha = 0.07f), thickness = 1.dp)
    }
}

@Composable
fun SystemScreen(
    uiState: CrownVaultUiState,
    onBack: () -> Unit,
    onNavigateSecurity: () -> Unit,
    onNavigateProfile: () -> Unit,
    onOpenLangModal: () -> Unit,
    onOpenSettingsModal: (SettingsModalType) -> Unit,
    onRunScan: () -> Unit
) {
    BackHandler(onBack = onBack)
    val t = uiState.t
    Column(modifier = Modifier.fillMaxWidth()) {
        PageIntroSection(
            kicker = t.system.kicker,
            title = t.system.title,
            subtitle = t.system.subtitle
        )

        ProfileIdentityBanner(
            displayName = uiState.displayName,
            address = uiState.walletAddressShort,
            verifiedLabel = t.common.verified,
            onClick = onNavigateProfile
        )

        Spacer(modifier = Modifier.height(20.dp))

        Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
            SettingItemRow(
                icon = Icons.Outlined.VerifiedUser,
                title = t.system.securityCenter,
                detail = if (uiState.systemScanned) t.common.verified else t.system.allSystemsNominal,
                accentGreen = true,
                tag = "setting_security_center",
                onClick = onNavigateSecurity
            )
            SettingItemRow(
                icon = Icons.Outlined.DarkMode,
                title = t.system.appearance,
                detail = when (uiState.themeMode) {
                    "neon" -> t.system.themeNeon
                    "auto" -> t.system.themeAuto
                    else -> t.system.appearanceDetail
                },
                accentGreen = false,
                tag = "setting_appearance",
                onClick = { onOpenSettingsModal(SettingsModalType.APPEARANCE) }
            )
            SettingItemRow(
                icon = Icons.Outlined.Hub,
                title = t.system.networkPref,
                detail = when (uiState.networkPref) {
                    "quantum" -> t.system.netQuantumMesh
                    "direct" -> t.system.netDirectLink
                    else -> t.system.networkDetail
                },
                accentGreen = false,
                tag = "setting_network_pref",
                onClick = { onOpenSettingsModal(SettingsModalType.NETWORK) }
            )
            SettingItemRow(
                icon = Icons.Outlined.Language,
                title = t.system.language,
                detail = t.system.languageDetail,
                accentGreen = false,
                tag = "setting_language",
                onClick = onOpenLangModal
            )
            SettingItemRow(
                icon = Icons.Outlined.Notifications,
                title = t.system.notifications,
                detail = t.system.notificationsDetail,
                accentGreen = false,
                tag = "setting_notifications",
                onClick = { onOpenSettingsModal(SettingsModalType.NOTIFICATIONS) }
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Run Security Scan Outline Button
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .clip(RoundedCornerShape(13.dp))
                .border(1.dp, Color.White.copy(alpha = 0.14f), RoundedCornerShape(13.dp))
                .clickable(enabled = !uiState.systemScanning, onClick = onRunScan)
                .padding(horizontal = 16.dp)
                .testTag("system_run_scan_button"),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (uiState.systemScanning) {
                CircularProgressIndicator(
                    color = CrownColors.NeonCyan,
                    strokeWidth = 2.dp,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = t.system.scanning,
                    color = Color(0xFFB8C0D9),
                    fontSize = 13.sp,
                    fontWeight = FontWeight.SemiBold
                )
            } else {
                Icon(
                    imageVector = Icons.Outlined.QrCodeScanner,
                    contentDescription = null,
                    tint = Color(0xFFB8C0D9),
                    modifier = Modifier.size(17.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = t.system.runSecurityScan,
                    color = Color(0xFFB8C0D9),
                    fontSize = 13.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        ZeroKnowledgeBadge(text = t.common.zeroKnowledgeBadge)

        Spacer(modifier = Modifier.height(22.dp))

        Text(
            text = "${t.system.osVersion} v2.389.04",
            color = CrownColors.TextSubtle,
            fontSize = 10.sp,
            fontFamily = FontFamily.Monospace,
            letterSpacing = 0.8.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun ActivityScreen(
    uiState: CrownVaultUiState,
    onBack: () -> Unit,
    onFilterChange: (String) -> Unit,
    onToggleExpand: (String) -> Unit,
    onNotify: (String) -> Unit
) {
    BackHandler(onBack = onBack)
    val context = LocalContext.current
    val activities = uiState.filteredActivities
    val depositCount = uiState.activities.count { it.type == ActivityType.DEPOSIT }
    val sendCount = uiState.activities.count { it.type == ActivityType.SEND }
    val swapCount = uiState.activities.count { it.type == ActivityType.SWAP }

    Column(modifier = Modifier.fillMaxWidth()) {
        PageIntroSection(
            kicker = "LEDGER FEED",
            title = "Activity",
            subtitle = "Recent deposits, outgoing sends, and cross-chain swaps verified on Crownlink."
        )

        // Summary Metrics Row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(15.dp))
                .background(CrownColors.SurfaceGlass)
                .border(1.dp, CrownColors.SurfaceBorder, RoundedCornerShape(15.dp))
                .padding(horizontal = 14.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "7D VOLUME",
                    color = CrownColors.TextMuted,
                    fontSize = 9.sp,
                    fontFamily = FontFamily.Monospace,
                    letterSpacing = 1.1.sp
                )
                Spacer(modifier = Modifier.height(3.dp))
                Text(
                    text = "$27,181.28",
                    color = CrownColors.TextPrimary,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = FontFamily.Monospace
                )
            }
            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "TRANSACTIONS",
                    color = CrownColors.TextMuted,
                    fontSize = 9.sp,
                    fontFamily = FontFamily.Monospace,
                    letterSpacing = 1.1.sp
                )
                Spacer(modifier = Modifier.height(3.dp))
                Text(
                    text = "${uiState.activities.size} Confirmed",
                    color = CrownColors.PositiveMint,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = FontFamily.Monospace
                )
            }
            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = "FINALITY",
                    color = CrownColors.TextMuted,
                    fontSize = 9.sp,
                    fontFamily = FontFamily.Monospace,
                    letterSpacing = 1.1.sp
                )
                Spacer(modifier = Modifier.height(3.dp))
                Text(
                    text = "0.4s ZK",
                    color = CrownColors.NeonCyan,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = FontFamily.Monospace
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Filter Chips: All, Deposits, Sends, Swaps
        val filterOptions = listOf(
            Triple("all", "All", uiState.activities.size),
            Triple("deposit", "Deposits", depositCount),
            Triple("send", "Sends", sendCount),
            Triple("swap", "Swaps", swapCount)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            filterOptions.forEach { (key, label, count) ->
                val selected = uiState.activityFilter == key
                Row(
                    modifier = Modifier
                        .heightIn(min = 40.dp)
                        .clip(RoundedCornerShape(11.dp))
                        .background(
                            if (selected) Color(0x2453EBD3) else CrownColors.SurfaceGlass
                        )
                        .border(
                            1.dp,
                            if (selected) CrownColors.NeonCyan else CrownColors.SurfaceBorder,
                            RoundedCornerShape(11.dp)
                        )
                        .clickable { onFilterChange(key) }
                        .padding(horizontal = 13.dp, vertical = 8.dp)
                        .testTag("activity_filter_$key"),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Text(
                        text = label,
                        color = if (selected) CrownColors.NeonCyan else CrownColors.TextSecondary,
                        fontSize = 12.sp,
                        fontWeight = if (selected) FontWeight.SemiBold else FontWeight.Medium
                    )
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(5.dp))
                            .background(
                                if (selected) CrownColors.NeonCyan.copy(alpha = 0.2f)
                                else CrownColors.SurfaceBorder
                            )
                            .padding(horizontal = 5.dp, vertical = 1.dp)
                    ) {
                        Text(
                            text = count.toString(),
                            color = if (selected) CrownColors.NeonCyan else CrownColors.TextMuted,
                            fontSize = 9.sp,
                            fontFamily = FontFamily.Monospace,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Column(verticalArrangement = Arrangement.spacedBy(9.dp)) {
            activities.forEach { item ->
                val isExpanded = uiState.expandedActivityId == item.id
                val typeIcon = when (item.type) {
                    ActivityType.DEPOSIT -> Icons.Default.SouthWest
                    ActivityType.SEND -> Icons.Default.NorthEast
                    ActivityType.SWAP -> Icons.AutoMirrored.Filled.CompareArrows
                }
                val typeColor = when (item.type) {
                    ActivityType.DEPOSIT -> CrownColors.PositiveMint
                    ActivityType.SEND -> CrownColors.WarmAmber
                    ActivityType.SWAP -> CrownColors.AccentViolet
                }
                val typeBadgeBg = when (item.type) {
                    ActivityType.DEPOSIT -> Color(0x2250E2C7)
                    ActivityType.SEND -> Color(0x22FFB36E)
                    ActivityType.SWAP -> Color(0x24BE94FF)
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(15.dp))
                        .background(CrownColors.SurfaceGlass)
                        .border(
                            1.dp,
                            if (isExpanded) CrownColors.NeonCyan.copy(alpha = 0.55f) else CrownColors.SurfaceBorder,
                            RoundedCornerShape(15.dp)
                        )
                        .clickable { onToggleExpand(item.id) }
                        .padding(horizontal = 13.dp, vertical = 12.dp)
                        .testTag("activity_item_${item.id}")
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(11.dp)
                    ) {
                        val matchedAsset = uiState.assets.firstOrNull { it.icon == item.icon } ?: uiState.assets.first()
                        Box(contentAlignment = Alignment.BottomEnd) {
                            AssetIconBadge(
                                asset = matchedAsset,
                                boxSize = 40.dp,
                                iconSize = 20.dp
                            )
                            Box(
                                modifier = Modifier
                                    .offset(x = 3.dp, y = 3.dp)
                                    .size(18.dp)
                                    .clip(CircleShape)
                                    .background(Color(0xFF0B0D1D))
                                    .border(1.dp, typeColor, CircleShape),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = typeIcon,
                                    contentDescription = item.type.label,
                                    tint = typeColor,
                                    modifier = Modifier.size(11.dp)
                                )
                            }
                        }

                        Column(modifier = Modifier.weight(1f)) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(6.dp)
                            ) {
                                Text(
                                    text = item.title,
                                    color = CrownColors.TextPrimary,
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.SemiBold
                                )
                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(4.dp))
                                        .background(typeBadgeBg)
                                        .padding(horizontal = 5.dp, vertical = 1.dp)
                                ) {
                                    Text(
                                        text = item.type.label.uppercase(),
                                        color = typeColor,
                                        fontSize = 8.sp,
                                        fontFamily = FontFamily.Monospace,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.height(3.dp))
                            Text(
                                text = item.subtitle,
                                color = CrownColors.TextMuted,
                                fontSize = 10.sp,
                                fontFamily = FontFamily.Monospace
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = item.timestamp,
                                color = CrownColors.TextSubtle,
                                fontSize = 9.sp,
                                fontFamily = FontFamily.Monospace
                            )
                        }

                        Column(horizontalAlignment = Alignment.End) {
                            Text(
                                text = item.primaryAmount,
                                color = if (item.isPositive) CrownColors.PositiveMint else CrownColors.TextPrimary,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                fontFamily = FontFamily.Monospace
                            )
                            Spacer(modifier = Modifier.height(3.dp))
                            Text(
                                text = item.secondaryValue,
                                color = CrownColors.TextMuted,
                                fontSize = 10.sp,
                                fontFamily = FontFamily.Monospace
                            )
                        }
                    }

                    AnimatedVisibility(visible = isExpanded) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 12.dp)
                        ) {
                            HorizontalDivider(color = CrownColors.SurfaceBorder, thickness = 1.dp)
                            Spacer(modifier = Modifier.height(10.dp))
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "STATUS",
                                    color = CrownColors.TextMuted,
                                    fontSize = 9.sp,
                                    fontFamily = FontFamily.Monospace
                                )
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.CheckCircle,
                                        contentDescription = null,
                                        tint = CrownColors.PositiveMint,
                                        modifier = Modifier.size(13.dp)
                                    )
                                    Text(
                                        text = "${item.status} · ${item.network}",
                                        color = CrownColors.PositiveMint,
                                        fontSize = 10.sp,
                                        fontFamily = FontFamily.Monospace,
                                        fontWeight = FontWeight.SemiBold
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.height(8.dp))
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(9.dp))
                                    .background(Color.Black.copy(alpha = 0.25f))
                                    .border(1.dp, CrownColors.SurfaceBorder, RoundedCornerShape(9.dp))
                                    .clickable {
                                        copyToClipboard(context, item.txHash)
                                        onNotify("Transaction hash copied to clipboard!")
                                    }
                                    .padding(horizontal = 10.dp, vertical = 8.dp)
                                    .testTag("copy_tx_hash_${item.id}"),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "TX: ${item.txHash}",
                                    color = CrownColors.TextSecondary,
                                    fontSize = 10.sp,
                                    fontFamily = FontFamily.Monospace,
                                    maxLines = 1,
                                    modifier = Modifier
                                        .weight(1f)
                                        .padding(end = 8.dp)
                                )
                                Icon(
                                    imageVector = Icons.Outlined.ContentCopy,
                                    contentDescription = "Copy Transaction Hash",
                                    tint = CrownColors.NeonCyan,
                                    modifier = Modifier.size(15.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun SecurityScreen(
    uiState: CrownVaultUiState,
    onBack: () -> Unit,
    onToggleBiometric: () -> Unit = {},
    onToggleTwoFactor: () -> Unit = {},
    onToggleTxScanning: () -> Unit = {},
    onRunFullScan: () -> Unit
) {
    BackHandler(onBack = onBack)
    val t = uiState.t

    val infiniteTransition = rememberInfiniteTransition(label = "shieldScan")
    val pulseScale by infiniteTransition.animateFloat(
        initialValue = 0.94f,
        targetValue = 1.08f,
        animationSpec = infiniteRepeatable(
            animation = tween(700, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "shieldScale"
    )

    val animatedProgress by animateFloatAsState(
        targetValue = uiState.securityScanProgress,
        animationSpec = tween(320, easing = FastOutSlowInEasing),
        label = "securityScanProgressAnim"
    )

    val activeLayersCount = listOf(
        uiState.secBiometric,
        uiState.secTwoFactor,
        uiState.secTxScanning
    ).count { it }

    Column(modifier = Modifier.fillMaxWidth()) {
        PageIntroSection(
            kicker = t.security.kicker,
            title = t.security.title,
            subtitle = t.security.subtitle
        )

        // Security Shield Visual + Active Shield Score
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(84.dp)
                    .clip(CircleShape)
                    .background(
                        Brush.radialGradient(
                            colors = listOf(
                                CrownColors.NeonCyan.copy(alpha = 0.22f),
                                Color.Transparent
                            )
                        )
                    )
                    .border(
                        1.dp,
                        if (activeLayersCount == 3) CrownColors.NeonCyan.copy(alpha = 0.45f)
                        else CrownColors.WarmAmber.copy(alpha = 0.45f),
                        CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Outlined.VerifiedUser,
                    contentDescription = null,
                    tint = if (activeLayersCount == 3) CrownColors.NeonCyan else CrownColors.WarmAmber,
                    modifier = Modifier
                        .size(44.dp)
                        .scale(if (uiState.securityScanning) pulseScale else 1f)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "$activeLayersCount OF 3 CORE SHIELDS ACTIVE",
                color = if (activeLayersCount == 3) CrownColors.PositiveMint else CrownColors.WarmAmber,
                fontSize = 10.sp,
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.SemiBold,
                letterSpacing = 1.2.sp
            )
        }

        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            SecurityInteractiveToggleRow(
                icon = Icons.Outlined.VerifiedUser,
                title = t.security.biometricLock,
                detail = if (uiState.secBiometric) t.security.biometricDetail else "Face ID · Disabled",
                enabled = uiState.secBiometric,
                tag = "security_biometric_row",
                onToggle = onToggleBiometric
            )
            SecurityInteractiveToggleRow(
                icon = Icons.Outlined.Lock,
                title = t.security.twoFactor,
                detail = if (uiState.secTwoFactor) t.security.twoFactorDetail else "Authenticator · Paused",
                enabled = uiState.secTwoFactor,
                tag = "security_2fa_row",
                onToggle = onToggleTwoFactor
            )
            SecurityInteractiveToggleRow(
                icon = Icons.Outlined.QrCodeScanner,
                title = t.security.txScanning,
                detail = if (uiState.secTxScanning) t.security.txScanningDetail else "Real-time firewall · Off",
                enabled = uiState.secTxScanning,
                tag = "security_tx_scanning_row",
                onToggle = onToggleTxScanning
            )
            SettingItemRow(
                icon = Icons.Outlined.Hub,
                title = t.security.whitelisted,
                detail = t.security.whitelistedDetail,
                accentGreen = false,
                tag = "security_whitelisted_row"
            )
        }

        Spacer(modifier = Modifier.height(18.dp))

        CrownPrimaryButton(
            text = if (uiState.securityScanning) t.security.scanning else t.security.runFullScan,
            icon = if (uiState.securityScanning) null else Icons.Outlined.QrCodeScanner,
            tag = "security_full_scan_button",
            enabled = !uiState.securityScanning,
            onClick = onRunFullScan
        )

        Spacer(modifier = Modifier.height(16.dp))

        when {
            uiState.securityScanning -> {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(14.dp))
                        .background(Color(0x1453EBD3))
                        .border(1.dp, CrownColors.NeonCyan.copy(alpha = 0.45f), RoundedCornerShape(14.dp))
                        .padding(horizontal = 16.dp, vertical = 14.dp)
                        .testTag("security_scanning_progress_panel"),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(10.dp),
                            modifier = Modifier.weight(1f)
                        ) {
                            CircularProgressIndicator(
                                color = CrownColors.NeonCyan,
                                strokeWidth = 2.dp,
                                modifier = Modifier.size(18.dp)
                            )
                            Text(
                                text = uiState.securityScanStageText.ifEmpty { t.security.scanningLayers },
                                color = CrownColors.NeonCyan,
                                fontSize = 11.sp,
                                fontFamily = FontFamily.Monospace
                            )
                        }
                        Text(
                            text = "${(animatedProgress * 100).toInt()}%",
                            color = CrownColors.TextPrimary,
                            fontSize = 11.sp,
                            fontFamily = FontFamily.Monospace,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    // Sleek Linear Progress Bar
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(6.dp)
                            .clip(RoundedCornerShape(3.dp))
                            .background(Color.Black.copy(alpha = 0.35f))
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth(animatedProgress.coerceIn(0.05f, 1f))
                                .fillMaxHeight()
                                .clip(RoundedCornerShape(3.dp))
                                .background(
                                    Brush.horizontalGradient(
                                        colors = listOf(CrownColors.NeonCyan, CrownColors.AccentViolet)
                                    )
                                )
                        )
                    }
                }
            }
            uiState.securityScanned -> {
                InfoBannerPanel(
                    title = t.security.allNominal,
                    detail = "${t.security.zeroThreats} · ${t.security.lastScan}"
                )
            }
            else -> {
                InfoBannerPanel(
                    title = t.security.allNominal,
                    detail = t.security.lastScan
                )
            }
        }
    }
}

@Composable
private fun SecurityInteractiveToggleRow(
    icon: ImageVector,
    title: String,
    detail: String,
    enabled: Boolean,
    tag: String,
    onToggle: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 66.dp)
            .clip(RoundedCornerShape(14.dp))
            .background(
                if (enabled) CrownColors.SurfaceGlass else Color.Black.copy(alpha = 0.18f)
            )
            .border(
                1.dp,
                if (enabled) CrownColors.NeonCyan.copy(alpha = 0.32f) else CrownColors.SurfaceBorder,
                RoundedCornerShape(14.dp)
            )
            .clickable(onClick = onToggle)
            .padding(horizontal = 12.dp, vertical = 10.dp)
            .testTag(tag),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(11.dp)
    ) {
        Box(
            modifier = Modifier
                .size(36.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(
                    if (enabled) Color(0x2253E7D1) else Color.White.copy(alpha = 0.06f)
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = if (enabled) CrownColors.NeonCyan else CrownColors.TextMuted,
                modifier = Modifier.size(18.dp)
            )
        }
        Column(modifier = Modifier.weight(1f)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Text(
                    text = title,
                    color = CrownColors.TextPrimary,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium
                )
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(4.dp))
                        .background(
                            if (enabled) Color(0x2450E2C7) else Color(0x24FF8B8B)
                        )
                        .padding(horizontal = 5.dp, vertical = 1.dp)
                ) {
                    Text(
                        text = if (enabled) "ON" else "OFF",
                        color = if (enabled) CrownColors.PositiveMint else CrownColors.CoralAlert,
                        fontSize = 8.sp,
                        fontFamily = FontFamily.Monospace,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            Spacer(modifier = Modifier.height(3.dp))
            Text(
                text = detail,
                color = if (enabled) CrownColors.TextMuted else CrownColors.TextSubtle,
                fontSize = 10.sp,
                fontFamily = FontFamily.Monospace
            )
        }
        Switch(
            checked = enabled,
            onCheckedChange = { onToggle() },
            colors = SwitchDefaults.colors(
                checkedThumbColor = CrownColors.NeonCyan,
                checkedTrackColor = CrownColors.NeonCyan.copy(alpha = 0.32f),
                uncheckedThumbColor = CrownColors.TextMuted,
                uncheckedTrackColor = CrownColors.SurfaceBorder
            )
        )
    }
}

@Composable
fun ProfileScreen(
    uiState: CrownVaultUiState,
    onBack: () -> Unit,
    onOpenLegal: (LegalDoc) -> Unit,
    onExportProfile: () -> Unit,
    onOpenProfileModal: (ProfileModalType) -> Unit = {}
) {
    BackHandler(onBack = onBack)
    val t = uiState.t
    Column(modifier = Modifier.fillMaxWidth()) {
        PageIntroSection(
            kicker = t.profile.kicker,
            title = t.profile.title,
            subtitle = t.profile.subtitle
        )

        ProfileIdentityBanner(
            displayName = uiState.displayName,
            address = uiState.walletAddressShort,
            verifiedLabel = t.common.verified,
            onClick = { onOpenProfileModal(ProfileModalType.EDIT_NAME) }
        )

        Spacer(modifier = Modifier.height(20.dp))

        Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
            SettingItemRow(
                icon = Icons.Outlined.Person,
                title = t.profile.displayName,
                detail = uiState.displayName,
                accentGreen = false,
                tag = "profile_display_name_row",
                onClick = { onOpenProfileModal(ProfileModalType.EDIT_NAME) }
            )
            SettingItemRow(
                icon = Icons.Outlined.VerifiedUser,
                title = t.profile.verificationStatus,
                detail = t.profile.verifiedTier3,
                accentGreen = true,
                tag = "profile_verification_row",
                onClick = { onOpenProfileModal(ProfileModalType.VERIFICATION_STATUS) }
            )
            SettingItemRow(
                icon = Icons.Outlined.Hub,
                title = t.profile.walletAddress,
                detail = uiState.walletAddressShort,
                accentGreen = false,
                tag = "profile_wallet_address_row",
                onClick = { onOpenProfileModal(ProfileModalType.RECEIVE_WALLET) }
            )
        }

        Spacer(modifier = Modifier.height(18.dp))
        HorizontalDivider(color = Color.White.copy(alpha = 0.07f), thickness = 1.dp)
        Spacer(modifier = Modifier.height(16.dp))

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
            tag = "profile_legal_terms",
            onClick = { onOpenLegal(LegalDoc.TERMS) }
        )
        LegalItemRow(
            label = t.legal.privacy.title,
            tag = "profile_legal_privacy",
            onClick = { onOpenLegal(LegalDoc.PRIVACY) }
        )
        LegalItemRow(
            label = t.legal.usage.title,
            tag = "profile_legal_usage",
            onClick = { onOpenLegal(LegalDoc.USAGE) }
        )
        LegalItemRow(
            label = t.legal.disclaimer.title,
            tag = "profile_legal_disclaimer",
            onClick = { onOpenLegal(LegalDoc.DISCLAIMER) }
        )

        Spacer(modifier = Modifier.height(18.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .clip(RoundedCornerShape(13.dp))
                .border(1.dp, Color.White.copy(alpha = 0.14f), RoundedCornerShape(13.dp))
                .clickable(onClick = onExportProfile)
                .padding(horizontal = 16.dp)
                .testTag("export_profile_button"),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Outlined.FileDownload,
                contentDescription = null,
                tint = Color(0xFFB8C0D9),
                modifier = Modifier.size(17.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = t.profile.exportData,
                color = Color(0xFFB8C0D9),
                fontSize = 13.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

@Composable
private fun PageIntroSection(
    kicker: String,
    title: String,
    subtitle: String
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp, bottom = 22.dp)
    ) {
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
            fontSize = 28.sp,
            fontWeight = FontWeight.Medium,
            letterSpacing = (-0.8).sp
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = subtitle,
            color = CrownColors.TextMuted,
            fontSize = 12.sp,
            lineHeight = 18.sp
        )
    }
}

@Composable
private fun ProfileIdentityBanner(
    displayName: String,
    address: String,
    verifiedLabel: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(15.dp))
            .background(CrownColors.SurfaceGlass)
            .border(1.dp, CrownColors.SurfaceBorder, RoundedCornerShape(15.dp))
            .clickable(onClick = onClick)
            .padding(16.dp)
            .testTag("profile_identity_banner"),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(11.dp)
    ) {
        Box(
            modifier = Modifier
                .size(42.dp)
                .clip(RoundedCornerShape(14.dp))
                .background(Color(0x1A5BEEDB))
                .border(1.dp, CrownColors.NeonCyan.copy(alpha = 0.45f), RoundedCornerShape(14.dp)),
            contentAlignment = Alignment.Center
        ) {
            CrownBrandIcon(size = 34.dp)
        }
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = displayName,
                color = CrownColors.TextPrimary,
                fontSize = 13.sp,
                fontWeight = FontWeight.Medium
            )
            Spacer(modifier = Modifier.height(3.dp))
            Text(
                text = address,
                color = CrownColors.TextMuted,
                fontSize = 10.sp,
                fontFamily = FontFamily.Monospace
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Icon(
                imageVector = Icons.Outlined.VerifiedUser,
                contentDescription = null,
                tint = CrownColors.NeonCyan,
                modifier = Modifier.size(14.dp)
            )
            Text(
                text = verifiedLabel,
                color = CrownColors.NeonCyan,
                fontSize = 10.sp,
                fontFamily = FontFamily.Monospace
            )
        }
    }
}

@Composable
private fun SettingItemRow(
    icon: ImageVector,
    title: String,
    detail: String,
    accentGreen: Boolean,
    tag: String,
    onClick: (() -> Unit)? = null
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 63.dp)
            .clip(RoundedCornerShape(14.dp))
            .background(CrownColors.SurfaceGlass)
            .border(1.dp, CrownColors.SurfaceBorder, RoundedCornerShape(14.dp))
            .then(if (onClick != null) Modifier.clickable(onClick = onClick) else Modifier)
            .padding(horizontal = 11.dp, vertical = 10.dp)
            .testTag(tag),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(11.dp)
    ) {
        Box(
            modifier = Modifier
                .size(34.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(if (accentGreen) Color(0x1A53E7D1) else Color(0x1ABB92FF)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = if (accentGreen) CrownColors.NeonCyan else CrownColors.AccentViolet,
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
            Spacer(modifier = Modifier.height(3.dp))
            Text(
                text = detail,
                color = CrownColors.TextMuted,
                fontSize = 10.sp,
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
private fun InfoBannerPanel(
    title: String,
    detail: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .background(Color(0x1453EBD3))
            .border(1.dp, CrownColors.NeonCyan.copy(alpha = 0.35f), RoundedCornerShape(14.dp))
            .padding(16.dp)
            .testTag("security_nominal_banner"),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Icon(
            imageVector = Icons.Default.CheckCircle,
            contentDescription = null,
            tint = CrownColors.PositiveMint,
            modifier = Modifier.size(20.dp)
        )
        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Text(
                text = title,
                color = CrownColors.TextPrimary,
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = detail,
                color = CrownColors.TextSecondary,
                fontSize = 11.sp,
                lineHeight = 16.sp
            )
        }
    }
}

@Composable
private fun ZeroKnowledgeBadge(text: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(Color(0x0F53EBD3))
            .border(1.dp, Color(0x2653EBD3), RoundedCornerShape(12.dp))
            .padding(horizontal = 14.dp, vertical = 12.dp),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Icon(
            imageVector = Icons.Outlined.VerifiedUser,
            contentDescription = null,
            tint = CrownColors.NeonCyan,
            modifier = Modifier
                .padding(top = 2.dp)
                .size(15.dp)
        )
        Text(
            text = text,
            color = Color(0xFF6FDBC4),
            fontSize = 10.sp,
            lineHeight = 15.sp
        )
    }
}
