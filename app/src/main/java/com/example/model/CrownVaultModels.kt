package com.example.model

enum class Screen(val route: String) {
    VAULT("vault"),
    MARKETS("markets"),
    STAKING("staking"),
    SWAP("swap"),
    CARD("card"),
    PLUS("plus"),
    ACTIVITY("activity"),
    SYSTEM("system"),
    SECURITY("security"),
    PROFILE("profile")
}

enum class ActivityType(val filterKey: String, val label: String) {
    DEPOSIT("deposit", "Deposit"),
    SEND("send", "Send"),
    SWAP("swap", "Swap")
}

data class ActivityTransaction(
    val id: String,
    val type: ActivityType,
    val title: String,
    val subtitle: String,
    val primaryAmount: String,
    val secondaryValue: String,
    val timestamp: String,
    val status: String = "Confirmed",
    val txHash: String,
    val network: String = "Crownlink · ZK-Rollup",
    val icon: AssetIconType = AssetIconType.CROWN,
    val isPositive: Boolean
)

val defaultActivities: List<ActivityTransaction> = listOf(
    ActivityTransaction(
        id = "tx_1",
        type = ActivityType.DEPOSIT,
        title = "Received Crown Coin",
        subtitle = "From 0x91F2...84E1 · Instant Deposit",
        primaryAmount = "+1,250.00 CRWN",
        secondaryValue = "+$9,593.75",
        timestamp = "Today, 14:28",
        txHash = "0x8F4C92A1E04C62F9B7102D4B892A1E04C62F92E1",
        icon = AssetIconType.CROWN,
        isPositive = true
    ),
    ActivityTransaction(
        id = "tx_2",
        type = ActivityType.SWAP,
        title = "Swapped ETH → CRWN",
        subtitle = "Crownlink Router · 0.02% Slippage",
        primaryAmount = "+680.00 CRWN",
        secondaryValue = "−1.48 ETH ($5,214.30)",
        timestamp = "Today, 11:15",
        txHash = "0x3D18A90E72B441C0823FA9014C88B2E1740AC109",
        icon = AssetIconType.ETH,
        isPositive = true
    ),
    ActivityTransaction(
        id = "tx_3",
        type = ActivityType.SEND,
        title = "Sent Bitcoin",
        subtitle = "To 0x4C80...19B4 · Hardware Vault",
        primaryAmount = "−0.0450 BTC",
        secondaryValue = "−$3,052.91",
        timestamp = "Yesterday, 19:42",
        txHash = "0x7A9184B200E4C89F7102D4B892A1E04C62F9A442",
        icon = AssetIconType.BTC,
        isPositive = false
    ),
    ActivityTransaction(
        id = "tx_4",
        type = ActivityType.DEPOSIT,
        title = "Received Solana",
        subtitle = "From Staking Yield · Epoch 612",
        primaryAmount = "+12.50 SOL",
        secondaryValue = "+$2,302.50",
        timestamp = "Yesterday, 09:10",
        txHash = "0x5E20C11B89A34F0912D4B892A1E04C62F9C83301",
        icon = AssetIconType.SOL,
        isPositive = true
    ),
    ActivityTransaction(
        id = "tx_5",
        type = ActivityType.SWAP,
        title = "Swapped USDT → SPHR",
        subtitle = "Quantum Mesh Liquidity Pool",
        primaryAmount = "+500.00 SPHR",
        secondaryValue = "−2,140.00 USDT ($2,140.00)",
        timestamp = "2 days ago",
        txHash = "0x9C04E71A55D83B1200E4C89F7102D4B892A177F0",
        icon = AssetIconType.SPHR,
        isPositive = true
    ),
    ActivityTransaction(
        id = "tx_6",
        type = ActivityType.SEND,
        title = "Sent Tether",
        subtitle = "To 0x82D1...3F09 · Orbital Settlement",
        primaryAmount = "−650.00 USDT",
        secondaryValue = "−$650.00",
        timestamp = "3 days ago",
        txHash = "0x1B66F09D44E21A887102D4B892A1E04C62F9E518",
        icon = AssetIconType.USDT,
        isPositive = false
    ),
    ActivityTransaction(
        id = "tx_7",
        type = ActivityType.DEPOSIT,
        title = "Received Ethereum",
        subtitle = "From 0x2F19...C408 · Bridge Inflow",
        primaryAmount = "+1.200 ETH",
        secondaryValue = "+$4,227.82",
        timestamp = "4 days ago",
        txHash = "0x6D42B88C19F03A777102D4B892A1E04C62F9B804",
        icon = AssetIconType.ETH,
        isPositive = true
    )
)

enum class ModalType {
    SEND,
    RECEIVE,
    NOTIFY,
    LANG
}

enum class SettingsModalType {
    APPEARANCE,
    NETWORK,
    NOTIFICATIONS
}

enum class ProfileModalType {
    EDIT_NAME,
    VERIFICATION_STATUS,
    RECEIVE_WALLET
}

enum class Plan {
    MONTHLY,
    ANNUAL
}

enum class AssetIconType {
    CROWN,
    BTC,
    ETH,
    SOL,
    SPHR,
    USDT
}

data class CandleBar(
    val open: Float,
    val high: Float,
    val low: Float,
    val close: Float,
    val volume: Float,
    val label: String
)

data class Asset(
    val name: String,
    val ticker: String,
    val value: String,
    val amount: String,
    val change: String,
    val tone: String,
    val icon: AssetIconType,
    val unitPrice: Double = 1.0,
    val balanceNumeric: Double = 0.0,
    val balanceUsd: String = "$0.00",
    val pairSymbol: String = "$ticker / USDT",
    val high24h: String = value,
    val low24h: String = value,
    val volume24h: String = "$124.8M",
    val marketCap: String = "$2.4B",
    val seriesByTimeframe: Map<String, List<Float>> = emptyMap()
) {
    val isPositiveChange: Boolean
        get() = !change.trim().startsWith("-")

    fun priceSeriesFor(timeframe: String): List<Float> {
        return seriesByTimeframe[timeframe]
            ?: seriesByTimeframe["1D"]
            ?: listOf(
                unitPrice.toFloat() * 0.94f,
                unitPrice.toFloat() * 0.96f,
                unitPrice.toFloat() * 0.95f,
                unitPrice.toFloat() * 0.98f,
                unitPrice.toFloat()
            )
    }

    fun candlesFor(timeframe: String): List<CandleBar> {
        val points = priceSeriesFor(timeframe)
        val timeLabels = timeLabelsFor(timeframe, points.size)
        return points.mapIndexed { idx, closeVal ->
            val prev = if (idx == 0) closeVal * 0.992f else points[idx - 1]
            val openVal = prev
            val spread = (kotlin.math.abs(closeVal - openVal).coerceAtLeast(closeVal * 0.004f))
            val highVal = maxOf(openVal, closeVal) + spread * (0.45f + (idx % 3) * 0.18f)
            val lowVal = minOf(openVal, closeVal) - spread * (0.40f + ((idx + 1) % 3) * 0.15f)
            val vol = 0.35f + ((idx * 37 + ticker.hashCode().let { kotlin.math.abs(it) }) % 65) / 100f
            CandleBar(
                open = openVal,
                high = highVal,
                low = lowVal,
                close = closeVal,
                volume = vol.coerceIn(0.2f, 1.0f),
                label = timeLabels.getOrElse(idx) { "" }
            )
        }
    }

    fun formatPrice(price: Float): String {
        val d = price.toDouble()
        return when {
            d >= 1000.0 -> "$%,.2f".format(java.util.Locale.US, d)
            d >= 1.0 -> "$%.2f".format(java.util.Locale.US, d)
            else -> "$%.4f".format(java.util.Locale.US, d)
        }
    }

    companion object {
        fun timeLabelsFor(timeframe: String, count: Int): List<String> {
            return when (timeframe) {
                "1H" -> List(count) { idx -> "-${(count - 1 - idx) * 5}m" }.mapIndexed { i, s -> if (i == count - 1) "Now" else s }
                "1D" -> listOf("00:00", "02:00", "04:00", "06:00", "08:00", "10:00", "12:00", "14:00", "16:00", "18:00", "20:00", "Now").take(count)
                "1W" -> listOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun", "Mon", "Tue", "Wed", "Thu", "Now").take(count)
                "1M" -> List(count) { idx -> "D${idx + 1}" }.mapIndexed { i, s -> if (i == count - 1) "Now" else s }
                "1Y" -> listOf("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec").take(count)
                else -> List(count) { "$it" }
            }
        }
    }
}

val defaultAssets = listOf(
    Asset(
        name = "Crown Coin",
        ticker = "CRWN",
        value = "$7.67",
        amount = "2,400.00 CRWN",
        change = "+8.24%",
        tone = "cyan",
        icon = AssetIconType.CROWN,
        unitPrice = 7.675,
        balanceNumeric = 2400.0,
        balanceUsd = "$18,420.00",
        pairSymbol = "CRWN / USDT",
        high24h = "$7.89",
        low24h = "$7.04",
        volume24h = "$84.6M",
        marketCap = "$1.84B",
        seriesByTimeframe = mapOf(
            "1H" to listOf(7.52f, 7.55f, 7.53f, 7.58f, 7.60f, 7.57f, 7.62f, 7.64f, 7.61f, 7.65f, 7.66f, 7.675f),
            "1D" to listOf(7.09f, 7.14f, 7.12f, 7.22f, 7.19f, 7.31f, 7.38f, 7.34f, 7.46f, 7.55f, 7.61f, 7.675f),
            "1W" to listOf(6.45f, 6.60f, 6.52f, 6.78f, 6.90f, 6.84f, 7.05f, 7.18f, 7.12f, 7.39f, 7.54f, 7.675f),
            "1M" to listOf(5.80f, 5.95f, 6.10f, 6.02f, 6.28f, 6.45f, 6.38f, 6.72f, 6.95f, 7.15f, 7.42f, 7.675f),
            "1Y" to listOf(3.20f, 3.65f, 4.10f, 3.95f, 4.50f, 4.88f, 5.30f, 5.15f, 5.90f, 6.45f, 7.10f, 7.675f)
        )
    ),
    Asset(
        name = "Bitcoin",
        ticker = "BTC",
        value = "$67,842.50",
        amount = "0.1842 BTC",
        change = "+4.81%",
        tone = "orange",
        icon = AssetIconType.BTC,
        unitPrice = 67842.50,
        balanceNumeric = 0.1842,
        balanceUsd = "$12,496.59",
        pairSymbol = "BTC / USDT",
        high24h = "$68,490.00",
        low24h = "$64,710.00",
        volume24h = "$34.2B",
        marketCap = "$1.34T",
        seriesByTimeframe = mapOf(
            "1H" to listOf(67410f, 67480f, 67390f, 67550f, 67620f, 67580f, 67690f, 67740f, 67710f, 67790f, 67815f, 67842.5f),
            "1D" to listOf(64730f, 65120f, 64980f, 65640f, 65890f, 65520f, 66310f, 66780f, 66590f, 67150f, 67520f, 67842.5f),
            "1W" to listOf(61800f, 62450f, 62100f, 63300f, 64100f, 63750f, 64900f, 65600f, 65200f, 66400f, 67100f, 67842.5f),
            "1M" to listOf(57400f, 58900f, 58200f, 60100f, 61500f, 60800f, 62900f, 64200f, 63600f, 65400f, 66800f, 67842.5f),
            "1Y" to listOf(38500f, 42100f, 46800f, 44900f, 51200f, 54800f, 58900f, 56400f, 61200f, 63900f, 66100f, 67842.5f)
        )
    ),
    Asset(
        name = "Ethereum",
        ticker = "ETH",
        value = "$3,523.18",
        amount = "2.745 ETH",
        change = "+12.40%",
        tone = "purple",
        icon = AssetIconType.ETH,
        unitPrice = 3523.18,
        balanceNumeric = 2.745,
        balanceUsd = "$9,671.13",
        pairSymbol = "ETH / USDT",
        high24h = "$3,588.00",
        low24h = "$3,134.50",
        volume24h = "$18.9B",
        marketCap = "$423.5B",
        seriesByTimeframe = mapOf(
            "1H" to listOf(3488f, 3495f, 3490f, 3502f, 3498f, 3508f, 3512f, 3509f, 3516f, 3519f, 3521f, 3523.18f),
            "1D" to listOf(3134f, 3178f, 3165f, 3224f, 3268f, 3251f, 3318f, 3375f, 3360f, 3428f, 3482f, 3523.18f),
            "1W" to listOf(2980f, 3045f, 3015f, 3110f, 3180f, 3155f, 3240f, 3310f, 3290f, 3385f, 3460f, 3523.18f),
            "1M" to listOf(2690f, 2750f, 2810f, 2785f, 2910f, 3020f, 2980f, 3140f, 3250f, 3340f, 3450f, 3523.18f),
            "1Y" to listOf(1980f, 2150f, 2340f, 2280f, 2560f, 2790f, 2940f, 2850f, 3120f, 3290f, 3410f, 3523.18f)
        )
    ),
    Asset(
        name = "Solana",
        ticker = "SOL",
        value = "$184.20",
        amount = "45.50 SOL",
        change = "+6.32%",
        tone = "green",
        icon = AssetIconType.SOL,
        unitPrice = 184.20,
        balanceNumeric = 45.50,
        balanceUsd = "$8,381.10",
        pairSymbol = "SOL / USDT",
        high24h = "$188.65",
        low24h = "$172.90",
        volume24h = "$4.72B",
        marketCap = "$86.4B",
        seriesByTimeframe = mapOf(
            "1H" to listOf(181.8f, 182.3f, 182.1f, 182.7f, 183.0f, 182.6f, 183.2f, 183.5f, 183.3f, 183.8f, 184.0f, 184.20f),
            "1D" to listOf(173.2f, 174.8f, 174.1f, 176.4f, 177.9f, 176.8f, 178.5f, 180.1f, 179.4f, 181.6f, 183.1f, 184.20f),
            "1W" to listOf(158.0f, 162.5f, 160.8f, 165.4f, 169.2f, 167.5f, 172.0f, 175.8f, 174.2f, 178.9f, 181.7f, 184.20f),
            "1M" to listOf(138.0f, 144.0f, 141.5f, 149.0f, 155.0f, 152.5f, 160.0f, 166.5f, 164.0f, 172.5f, 179.0f, 184.20f),
            "1Y" to listOf(64.0f, 78.5f, 92.0f, 88.0f, 108.0f, 124.0f, 139.0f, 132.0f, 151.0f, 165.0f, 176.0f, 184.20f)
        )
    ),
    Asset(
        name = "Sphere Token",
        ticker = "SPHR",
        value = "$14.82",
        amount = "320.00 SPHR",
        change = "+15.60%",
        tone = "purple",
        icon = AssetIconType.SPHR,
        unitPrice = 14.82,
        balanceNumeric = 320.0,
        balanceUsd = "$4,742.40",
        pairSymbol = "SPHR / USDT",
        high24h = "$15.40",
        low24h = "$12.82",
        volume24h = "$218.4M",
        marketCap = "$1.12B",
        seriesByTimeframe = mapOf(
            "1H" to listOf(14.35f, 14.42f, 14.38f, 14.50f, 14.56f, 14.52f, 14.61f, 14.68f, 14.64f, 14.73f, 14.78f, 14.82f),
            "1D" to listOf(12.82f, 13.05f, 12.96f, 13.30f, 13.54f, 13.42f, 13.78f, 14.05f, 13.94f, 14.32f, 14.60f, 14.82f),
            "1W" to listOf(10.90f, 11.35f, 11.15f, 11.80f, 12.25f, 12.05f, 12.70f, 13.20f, 13.05f, 13.75f, 14.35f, 14.82f),
            "1M" to listOf(8.60f, 9.10f, 8.95f, 9.75f, 10.40f, 10.15f, 11.10f, 11.85f, 12.30f, 13.10f, 14.05f, 14.82f),
            "1Y" to listOf(3.40f, 4.20f, 5.15f, 4.90f, 6.30f, 7.45f, 8.80f, 8.40f, 10.20f, 11.90f, 13.45f, 14.82f)
        )
    ),
    Asset(
        name = "Tether",
        ticker = "USDT",
        value = "$1.00",
        amount = "8,420.00 USDT",
        change = "+0.01%",
        tone = "teal",
        icon = AssetIconType.USDT,
        unitPrice = 1.00,
        balanceNumeric = 8420.0,
        balanceUsd = "$8,420.00",
        pairSymbol = "USDT / USD",
        high24h = "$1.001",
        low24h = "$0.999",
        volume24h = "$62.4B",
        marketCap = "$118.2B",
        seriesByTimeframe = mapOf(
            "1H" to listOf(1.0000f, 1.0001f, 0.9999f, 1.0002f, 1.0000f, 0.9998f, 1.0001f, 1.0000f, 1.0002f, 0.9999f, 1.0001f, 1.0000f),
            "1D" to listOf(0.9998f, 1.0001f, 0.9999f, 1.0003f, 1.0000f, 0.9997f, 1.0002f, 1.0001f, 0.9999f, 1.0002f, 1.0001f, 1.0000f),
            "1W" to listOf(0.9997f, 1.0002f, 0.9998f, 1.0004f, 1.0001f, 0.9996f, 1.0003f, 1.0000f, 0.9999f, 1.0003f, 1.0001f, 1.0000f),
            "1M" to listOf(0.9996f, 1.0003f, 0.9998f, 1.0005f, 1.0001f, 0.9995f, 1.0004f, 1.0001f, 0.9998f, 1.0002f, 1.0001f, 1.0000f),
            "1Y" to listOf(0.9995f, 1.0004f, 0.9997f, 1.0006f, 1.0002f, 0.9994f, 1.0005f, 1.0000f, 0.9998f, 1.0003f, 1.0001f, 1.0000f)
        )
    )
)

enum class Lang(
    val code: String,
    val nativeName: String,
    val englishName: String,
    val isRtl: Boolean = false
) {
    EN("en", "English", "English"),
    ES("es", "Español", "Spanish"),
    PT("pt", "Português", "Portuguese"),
    ZH("zh", "中文", "Chinese"),
    JA("ja", "日本語", "Japanese"),
    KO("ko", "한국어", "Korean"),
    DE("de", "Deutsch", "German"),
    FR("fr", "Français", "French"),
    AR("ar", "العربية", "Arabic", isRtl = true),
    RU("ru", "Русский", "Russian")
}

enum class LegalDoc {
    TERMS,
    PRIVACY,
    USAGE,
    DISCLAIMER
}

data class LegalContent(
    val title: String,
    val paragraphs: List<String>
)

data class NavStrings(
    val vault: String,
    val markets: String,
    val staking: String,
    val swap: String,
    val card: String,
    val plus: String,
    val system: String
)

data class TopbarStrings(
    val network: String,
    val live: String
)

data class VaultStrings(
    val balanceLabel: String,
    val send: String,
    val receive: String,
    val portfolio: String,
    val yourAssets: String,
    val manage: String,
    val earnYield: String,
    val earnYieldDetail: String,
    val vaultPlusPerks: String,
    val vaultPlusPerksDetail: String
)

data class MarketsStrings(
    val kicker: String,
    val title: String,
    val subtitle: String,
    val topMover: String,
    val neonIndex: String,
    val globalSynth: String,
    val addedToWatchlist: String,
    val marketOverview: String,
    val activeTrading: String,
    val buy: String,
    val sell: String,
    val orderAmount: String,
    val orderType: String,
    val marketOrder: String,
    val limitOrder: String,
    val placeOrder: String,
    val priceChart: String,
    val selectAsset: String
)

data class StakingStrings(
    val kicker: String,
    val title: String,
    val subtitle: String,
    val totalStaked: String,
    val earnedThisWeek: String,
    val startEarning: String,
    val currentApy: String,
    val lockPeriod: String,
    val flexible: String,
    val networkFee: String,
    val stakingActive: String,
    val stakeCrwn: String,
    val protectedBy: String,
    val protectedDetail: String
)

data class SwapStrings(
    val kicker: String,
    val title: String,
    val subtitle: String,
    val youPay: String,
    val youReceive: String,
    val balance: String,
    val bestRoute: String,
    val reviewSwap: String,
    val poweredBy: String,
    val router: String
)

data class CardStrings(
    val kicker: String,
    val title: String,
    val subtitle: String,
    val cardReady: String,
    val virtualCard: String,
    val details: String,
    val lockCard: String,
    val unlockCard: String,
    val recentSpend: String,
    val activity: String,
    val seeAll: String,
    val orbitalMarket: String,
    val today: String,
    val cashbackReward: String,
    val yesterday: String
)

data class PlusStrings(
    val kicker: String,
    val title: String,
    val subtitle: String,
    val startFreeTrial: String,
    val instantSettlement: String,
    val instantSettlementDetail: String,
    val doubleRewards: String,
    val doubleRewardsDetail: String,
    val priorityRouting: String,
    val priorityRoutingDetail: String,
    val curated: String,
    val joinMembers: String
)

data class SystemStrings(
    val kicker: String,
    val title: String,
    val subtitle: String,
    val securityCenter: String,
    val allSystemsNominal: String,
    val appearance: String,
    val appearanceDetail: String,
    val networkPref: String,
    val networkDetail: String,
    val notifications: String,
    val notificationsDetail: String,
    val runSecurityScan: String,
    val scanning: String,
    val osVersion: String,
    val language: String,
    val languageDetail: String,
    val appearanceTitle: String,
    val themeMidnight: String,
    val themeNeon: String,
    val themeAuto: String,
    val networkTitle: String,
    val netCrownlink: String,
    val netQuantumMesh: String,
    val netDirectLink: String,
    val notificationsTitle: String,
    val notifSmartAlerts: String,
    val notifSmartAlertsDetail: String,
    val notifPriceAlerts: String,
    val notifPriceAlertsDetail: String,
    val notifSecurityAlerts: String,
    val notifSecurityAlertsDetail: String,
    val notifPushEnabled: String,
    val themeApplied: String,
    val networkSwitched: String,
    val notifSettingsSaved: String
)

data class SecurityStrings(
    val kicker: String,
    val title: String,
    val subtitle: String,
    val biometricLock: String,
    val biometricDetail: String,
    val twoFactor: String,
    val twoFactorDetail: String,
    val txScanning: String,
    val txScanningDetail: String,
    val whitelisted: String,
    val whitelistedDetail: String,
    val runFullScan: String,
    val allNominal: String,
    val lastScan: String,
    val scanning: String,
    val systemsNominal: String,
    val zeroThreats: String,
    val scanningLayers: String
)

data class ProfileStrings(
    val kicker: String,
    val title: String,
    val subtitle: String,
    val displayName: String,
    val verificationStatus: String,
    val verifiedTier3: String,
    val walletAddress: String,
    val exportData: String
)

data class DrawerStrings(
    val settings: String,
    val settingsDetail: String,
    val security: String,
    val securityDetail: String,
    val notifications: String,
    val notificationsDetail: String,
    val profile: String,
    val profileDetail: String,
    val vaultBalance: String,
    val legal: String
)

data class ModalStrings(
    val inbox: String,
    val notifications: String,
    val allCaughtUp: String,
    val newActivity: String,
    val transferOut: String,
    val transferIn: String,
    val sendAssets: String,
    val receiveAssets: String,
    val pasteAddress: String,
    val continueBtn: String,
    val copyAddress: String,
    val transferPrepared: String,
    val addressCopied: String
)

data class LegalStrings(
    val terms: LegalContent,
    val privacy: LegalContent,
    val usage: LegalContent,
    val disclaimer: LegalContent
) {
    fun forDoc(doc: LegalDoc): LegalContent = when (doc) {
        LegalDoc.TERMS -> terms
        LegalDoc.PRIVACY -> privacy
        LegalDoc.USAGE -> usage
        LegalDoc.DISCLAIMER -> disclaimer
    }
}

data class CommonStrings(
    val verified: String,
    val now: String,
    val zeroKnowledgeBadge: String
)

data class VaultPlusStrings(
    val header: String,
    val badge: String,
    val monthly: String,
    val annual: String,
    val save25: String,
    val perMonth: String,
    val perYear: String,
    val featureInstantTitle: String,
    val featureInstantDetail: String,
    val featureStakingTitle: String,
    val featureStakingDetail: String,
    val featurePriorityTitle: String,
    val featurePriorityDetail: String,
    val featureShieldTitle: String,
    val featureShieldDetail: String,
    val featureCardTitle: String,
    val featureCardDetail: String,
    val ctaButton: String,
    val footer: String,
    val statusActive: String,
    val freemiumMessage: String,
    val freeTierNote: String
)

data class ToastStrings(
    val stakingActive: String,
    val rateUpdated: String,
    val trialStarted: String,
    val cardDetailsCopied: String,
    val cardLocked: String,
    val cardUnlocked: String,
    val scanComplete: String,
    val profileExported: String,
    val vaultPlusTrial: String,
    val orderPlaced: String
)

data class TranslationSet(
    val nav: NavStrings,
    val topbar: TopbarStrings,
    val vault: VaultStrings,
    val markets: MarketsStrings,
    val staking: StakingStrings,
    val swap: SwapStrings,
    val card: CardStrings,
    val plus: PlusStrings,
    val system: SystemStrings,
    val security: SecurityStrings,
    val profile: ProfileStrings,
    val drawer: DrawerStrings,
    val modal: ModalStrings,
    val legal: LegalStrings,
    val common: CommonStrings,
    val vaultPlus: VaultPlusStrings,
    val toast: ToastStrings
)
