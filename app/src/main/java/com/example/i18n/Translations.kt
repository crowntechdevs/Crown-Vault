package com.example.i18n

import com.example.model.*
import java.util.Locale

fun detectDefaultLang(): Lang {
    val languageCode = Locale.getDefault().language.lowercase()
    return Lang.entries.firstOrNull { languageCode.startsWith(it.code) } ?: Lang.EN
}

private const val ZERO_KNOWLEDGE_BADGE =
    "Zero-Knowledge Secured: Crown Vault never stores your private keys, card numbers, or personal identity data. All encryption is local on your device."
private const val FREEMIUM_MESSAGE =
    "Enjoy Vault for free, or upgrade to Vault+ for premium limits"
private const val FREE_TIER_NOTE =
    "No credit card required. Close anytime to stay on the free tier."

val translations: Map<Lang, TranslationSet> = mapOf(
    Lang.EN to TranslationSet(
        nav = NavStrings("Vault", "Markets", "Staking", "Swap", "Card", "Vault+", "System"),
        topbar = TopbarStrings("Network: Crownlink", "LIVE"),
        vault = VaultStrings(
            "TOTAL VAULT BALANCE", "Send", "Receive", "PORTFOLIO", "Your assets",
            "Manage", "Earn yield", "Up to 14.2% APY", "Vault+ perks", "3 new benefits"
        ),
        markets = MarketsStrings(
            "INTELLIGENCE FEED", "Market pulse", "Real-time signals from across the Crownlink network.",
            "TOP MOVER", "Neon Index", "Global synthetics · 24H", "added to your watchlist",
            "Market Overview", "Active Trading", "Buy", "Sell", "Order amount", "Order type",
            "Market", "Limit", "Place order", "Price chart", "Select asset to trade"
        ),
        staking = StakingStrings(
            "PASSIVE YIELD", "Stake & grow", "Put your assets to work in the quantum economy.",
            "Total staked", "+$18.42 earned this week", "Start earning today", "Current APY",
            "Lock period", "Flexible", "Network fee", "Staking active", "Stake CRWN",
            "Protected by Crownlink", "Assets are secured by multi-layer validation and an audited protocol."
        ),
        swap = SwapStrings(
            "INSTANT EXCHANGE", "Quantum swap", "Trade across the vault. No friction, no noise.",
            "YOU PAY", "YOU RECEIVE", "Balance", "Best route", "Review swap",
            "Powered by", "CROWNLINK ROUTER"
        ),
        card = CardStrings(
            "THE FUTURE OF SPENDING", "Quantum Card", "One card. Every dimension.",
            "Card ready", "Virtual card · ending in 2389", "Details", "Lock card", "Unlock card",
            "RECENT SPEND", "Activity", "See all", "Orbital Market", "Today, 10:42",
            "Cashback reward", "Yesterday, 18:20"
        ),
        plus = PlusStrings(
            "THE UPGRADE", "Vault", "Unlock the full spectrum of your wealth.",
            "Start free trial", "Instant settlement", "Move assets in under 2 seconds",
            "2× rewards", "Earn more on every card spend", "Priority routing",
            "Access the fastest market paths", "Curated for the next era", "Join 18,402 early members"
        ),
        system = SystemStrings(
            "CORE SETTINGS", "System", "Your vault, tuned to your frequency.",
            "Security center", "All systems nominal", "Appearance", "Midnight / Neon",
            "Network preference", "Crownlink · 12 ms", "Notifications", "Smart alerts enabled",
            "Run security scan", "Scanning…", "CROWN VAULT OS", "Language", "English",
            "Appearance", "Midnight", "Neon", "Auto", "Network preference",
            "Crownlink · 12 ms", "Quantum Mesh · 28 ms", "Direct Link · 45 ms",
            "Notifications", "Smart alerts", "AI-powered transaction alerts",
            "Price alerts", "Notify on significant price movements",
            "Security alerts", "Critical security events", "Push notifications enabled",
            "Theme applied", "Network switched successfully", "Notification settings saved"
        ),
        security = SecurityStrings(
            "PROTECTION CENTER", "Security", "Your vault, fortified at every layer.",
            "Biometric lock", "Face ID · Enabled", "Two-factor auth", "Authenticator active",
            "Transaction scanning", "Real-time monitoring", "Whitelisted addresses", "3 trusted addresses",
            "Run full security scan", "All systems nominal",
            "Last scan: just now · 0 threats detected across all layers.",
            "Scanning…", "Systems Nominal", "Zero threats detected", "Scanning all security layers…"
        ),
        profile = ProfileStrings(
            "YOUR IDENTITY", "Profile", "Manage your Crown Vault identity.",
            "Display name", "Verification status", "Verified · Tier 3",
            "Wallet address", "Export profile data"
        ),
        drawer = DrawerStrings(
            "Settings", "App preferences", "Security", "Protection center",
            "Notifications", "Alert inbox", "Profile", "Traveler 07 · Verified",
            "VAULT BALANCE", "Legal"
        ),
        modal = ModalStrings(
            "INBOX", "Notifications", "You're all caught up", "New activity will appear here.",
            "TRANSFER OUT", "TRANSFER IN", "Send assets", "Receive assets",
            "Paste a wallet address", "Continue", "Copy address",
            "Transfer prepared for review", "Address copied to clipboard"
        ),
        legal = LegalStrings(
            terms = LegalContent(
                "Terms & Conditions",
                listOf(
                    "By accessing and using Crown Vault, you agree to be bound by these Terms and Conditions. Crown Tech™ provides the platform as a digital asset management tool.",
                    "Use of the application implies you are of legal age and responsible for your private keys, transactions, and financial decisions. Crown Tech™ is not liable for losses arising from misuse of the platform.",
                    "The platform may be updated, modified, or discontinued at any time without prior notice. Continued use constitutes acceptance of any changes."
                )
            ),
            privacy = LegalContent(
                "Privacy Policy",
                listOf(
                    "Crown Vault prioritizes your privacy. We do not collect unnecessary personal data or sell your information to third parties.",
                    "Your wallet data, transactions, and preferences are stored encrypted and locally when possible. Transactions are processed through the Crownlink network without intermediaries.",
                    "We may collect anonymous usage metrics to improve the experience. These metrics do not allow personal identification."
                )
            ),
            usage = LegalContent(
                "Terms of Use",
                listOf(
                    "Crown Vault is designed as a crypto asset management tool. Use of the application is at your own risk.",
                    "It is not permitted to use the platform for illicit activities, money laundering, or any action that violates applicable laws in your jurisdiction.",
                    "Staking, swap, and card features may be subject to regional availability. Crown Tech™ reserves the right to limit or suspend access in case of misuse."
                )
            ),
            disclaimer = LegalContent(
                "Disclaimer",
                listOf(
                    "Crown Vault is a demonstration application for educational and visualization purposes. The balances, assets, and transactions shown are simulated and do not represent real financial assets.",
                    "Crown Tech™ does not offer financial advice. Cryptocurrencies and digital assets carry significant risks, including total capital loss. Consult a professional advisor before making investment decisions.",
                    "This application does not constitute an offer of regulated financial services. All trademarks, logos, and designs are property of Crown Tech™."
                )
            )
        ),
        common = CommonStrings("Verified", "NOW", ZERO_KNOWLEDGE_BADGE),
        vaultPlus = VaultPlusStrings(
            "Unlock Vault+", "7-Day Free Trial", "Monthly", "Annual", "Save 25%", "/mo", "/yr",
            "Instant Settlement", "Zero-delay asset transfers across the Crownlink network.",
            "2x Staking Rewards", "Boosted yield on all staked assets (CRWN, ETH, BTC).",
            "Priority Routing", "Access to high-speed liquid routes with lower network fees.",
            "Advanced Security Shield", "Multi-layer vault encryption & real-time threat detection.",
            "Quantum Virtual Card", "Unlimited virtual card creation with zero FX fees.",
            "Start 7-Day Free Trial ($9.99/mo after)", "Cancel anytime in settings. Secured by Crownlink Protocol.",
            "Vault+ Active", FREEMIUM_MESSAGE, FREE_TIER_NOTE
        ),
        toast = ToastStrings(
            "CRWN is now generating yield", "Rate updated", "Vault+ trial started",
            "Virtual card details copied", "Card temporarily locked", "Card unlocked successfully",
            "Security scan complete — no issues found", "Profile exported successfully",
            "Welcome to Vault+! Trial started successfully", "Order placed successfully"
        )
    ),
    Lang.ES to TranslationSet(
        nav = NavStrings("Bóveda", "Mercados", "Staking", "Swap", "Tarjeta", "Vault+", "Sistema"),
        topbar = TopbarStrings("Red: Crownlink", "EN VIVO"),
        vault = VaultStrings(
            "SALDO TOTAL DE LA BÓVEDA", "Enviar", "Recibir", "PORTAFOLIO", "Tus activos",
            "Gestionar", "Generar rendimiento", "Hasta 14.2% APY", "Beneficios Vault+", "3 nuevos beneficios"
        ),
        markets = MarketsStrings(
            "FEED DE INTELIGENCIA", "Pulso del mercado", "Señales en tiempo real desde la red Crownlink.",
            "TOP MOVEDOR", "Neon Index", "Sintéticos globales · 24H", "añadido a tu watchlist",
            "Resumen del mercado", "Trading activo", "Comprar", "Vender", "Cantidad", "Tipo de orden",
            "Mercado", "Límite", "Enviar orden", "Gráfico de precio", "Seleccionar activo"
        ),
        staking = StakingStrings(
            "RENDIMIENTO PASIVO", "Stake y crece", "Pon tus activos a trabajar en la economía cuántica.",
            "Total en stake", "+$18.42 ganados esta semana", "Empieza a ganar hoy", "APY actual",
            "Período de bloqueo", "Flexible", "Comisión de red", "Staking activo", "Hacer stake de CRWN",
            "Protegido por Crownlink", "Los activos están asegurados por validación multicapa y un protocolo auditado."
        ),
        swap = SwapStrings(
            "INTERCAMBIO INSTANTÁNEO", "Quantum swap", "Intercambia dentro de la bóveda. Sin fricción, sin ruido.",
            "TÚ PAGAS", "TÚ RECIBES", "Saldo", "Mejor ruta", "Revisar swap",
            "Desarrollado por", "CROWNLINK ROUTER"
        ),
        card = CardStrings(
            "EL FUTURO DEL GASTO", "Quantum Card", "Una tarjeta. Cada dimensión.",
            "Tarjeta lista", "Tarjeta virtual · termina en 2389", "Detalles", "Bloquear tarjeta", "Desbloquear tarjeta",
            "GASTOS RECIENTES", "Actividad", "Ver todo", "Orbital Market", "Hoy, 10:42",
            "Recompensa cashback", "Ayer, 18:20"
        ),
        plus = PlusStrings(
            "LA ACTUALIZACIÓN", "Vault", "Desbloquea el espectro completo de tu riqueza.",
            "Prueba gratis", "Liquidación instantánea", "Mueve activos en menos de 2 segundos",
            "2× recompensas", "Gana más en cada gasto con tarjeta", "Enrutamiento prioritario",
            "Accede a las rutas más rápidas del mercado", "Curado para la próxima era", "Únete a 18,402 miembros early"
        ),
        system = SystemStrings(
            "AJUSTES PRINCIPALES", "Sistema", "Tu bóveda, sintonizada a tu frecuencia.",
            "Centro de seguridad", "Todos los sistemas nominales", "Apariencia", "Medianoche / Neón",
            "Preferencia de red", "Crownlink · 12 ms", "Notificaciones", "Alertas inteligentes activadas",
            "Escanear seguridad", "Escaneando…", "CROWN VAULT OS", "Idioma", "Español",
            "Apariencia", "Medianoche", "Neón", "Auto", "Preferencia de red",
            "Crownlink · 12 ms", "Quantum Mesh · 28 ms", "Direct Link · 45 ms",
            "Notificaciones", "Alertas inteligentes", "Alertas de transacción con IA",
            "Alertas de precio", "Notificar movimientos de precio significativos",
            "Alertas de seguridad", "Eventos de seguridad críticos", "Notificaciones push activadas",
            "Tema aplicado", "Red cambiada con éxito", "Ajustes de notificaciones guardados"
        ),
        security = SecurityStrings(
            "CENTRO DE PROTECCIÓN", "Seguridad", "Tu bóveda, fortificada en cada capa.",
            "Bloqueo biométrico", "Face ID · Activado", "Autenticación de dos factores", "Autenticador activo",
            "Escaneo de transacciones", "Monitoreo en tiempo real", "Direcciones de confianza", "3 direcciones verificadas",
            "Escanear seguridad completa", "Todos los sistemas nominales",
            "Último escaneo: ahora · 0 amenazas detectadas en todas las capas.",
            "Escaneando…", "Sistemas nominales", "Cero amenazas detectadas", "Escaneando todas las capas de seguridad…"
        ),
        profile = ProfileStrings(
            "TU IDENTIDAD", "Perfil", "Gestiona tu identidad de Crown Vault.",
            "Nombre visible", "Estado de verificación", "Verificado · Nivel 3",
            "Dirección de wallet", "Exportar datos de perfil"
        ),
        drawer = DrawerStrings(
            "Configuración", "Preferencias de la app", "Seguridad", "Centro de protección",
            "Notificaciones", "Bandeja de alertas", "Perfil", "Traveler 07 · Verificado",
            "SALDO DE BÓVEDA", "Legal"
        ),
        modal = ModalStrings(
            "BANDEJA", "Notificaciones", "Estás al día", "La nueva actividad aparecerá aquí.",
            "TRANSFERENCIA SALIENTE", "TRANSFERENCIA ENTRANTE", "Enviar activos", "Recibir activos",
            "Pega una dirección de wallet", "Continuar", "Copiar dirección",
            "Transferencia preparada para revisión", "Dirección copiada al portapapeles"
        ),
        legal = LegalStrings(
            terms = LegalContent(
                "Términos y Condiciones",
                listOf(
                    "Al acceder y utilizar Crown Vault, aceptás estar sujeto a estos Términos y Condiciones. Crown Tech™ proporciona la plataforma como una herramienta de gestión de activos digitales.",
                    "El uso de la aplicación implica que sos mayor de edad y responsable de tus claves privadas, transacciones y decisiones financieras. Crown Tech™ no se hace responsable por pérdidas derivadas del uso indebido de la plataforma.",
                    "La plataforma puede actualizarse, modificarse o descontinuarse en cualquier momento sin previo aviso. El uso continuado constituye la aceptación de cualquier cambio."
                )
            ),
            privacy = LegalContent(
                "Política de Privacidad",
                listOf(
                    "Crown Vault prioriza tu privacidad. No recopilamos datos personales innecesarios ni vendemos tu información a terceros.",
                    "Los datos de tu wallet, transacciones y preferencias se almacenan de forma cifrada y local cuando es posible. Las transacciones se procesan a través de la red Crownlink sin intermediarios.",
                    "Podemos recopilar métricas anónimas de uso para mejorar la experiencia. Estas métricas no permiten identificarte personalmente."
                )
            ),
            usage = LegalContent(
                "Términos de Uso",
                listOf(
                    "Crown Vault está diseñado como una herramienta de gestión de criptoactivos. El uso de la aplicación es bajo tu propio riesgo.",
                    "No está permitido usar la plataforma para actividades ilícitas, lavado de dinero, o cualquier acción que viole las leyes aplicables en tu jurisdicción.",
                    "Las funcionalidades de staking, swap y tarjetas pueden estar sujetas a disponibilidad regional. Crown Tech™ se reserva el derecho de limitar o suspender el acceso en caso de uso indebido."
                )
            ),
            disclaimer = LegalContent(
                "Disclaimer",
                listOf(
                    "Crown Vault es una aplicación de demostración con fines educativos y de visualización. Los saldos, activos y transacciones mostrados son simulados y no representan activos financieros reales.",
                    "Crown Tech™ no ofrece asesoramiento financiero. Las criptomonedas y activos digitales conllevan riesgos significativos, incluyendo la pérdida total del capital. Consultá a un asesor profesional antes de tomar decisiones de inversión.",
                    "Esta aplicación no constituye una oferta de servicios financieros regulados. Toda marca, logo y diseño son propiedad de Crown Tech™."
                )
            )
        ),
        common = CommonStrings("Verificado", "AHORA", ZERO_KNOWLEDGE_BADGE),
        vaultPlus = VaultPlusStrings(
            "Desbloquear Vault+", "Prueba Gratis de 7 Días", "Mensual", "Anual", "Ahorra 25%", "/mes", "/año",
            "Liquidación Instantánea", "Transferencias de activos sin demora en la red Crownlink.",
            "2x Recompensas de Staking", "Rendimiento aumentado en todos los activos en stake (CRWN, ETH, BTC).",
            "Enrutamiento Prioritario", "Acceso a rutas líquidas de alta velocidad con menores comisiones.",
            "Escudo de Seguridad Avanzado", "Cifrado multicapa del vault y detección de amenazas en tiempo real.",
            "Tarjeta Virtual Cuántica", "Creación ilimitada de tarjetas virtuales sin comisiones FX.",
            "Iniciar Prueba Gratis de 7 Días ($9.99/mes después)", "Cancela cuando quieras en ajustes. Protegido por Crownlink Protocol.",
            "Vault+ Activo", FREEMIUM_MESSAGE, FREE_TIER_NOTE
        ),
        toast = ToastStrings(
            "CRWN ahora está generando rendimiento", "Cotización actualizada", "Prueba de Vault+ iniciada",
            "Detalles de tarjeta copiados", "Tarjeta bloqueada temporalmente", "Tarjeta desbloqueada",
            "Escaneo de seguridad completo — sin problemas", "Perfil exportado exitosamente",
            "¡Bienvenido a Vault+! Prueba iniciada con éxito", "Orden enviada con éxito"
        )
    ),
    Lang.PT to TranslationSet(
        nav = NavStrings("Cofre", "Mercados", "Staking", "Swap", "Cartão", "Vault+", "Sistema"),
        topbar = TopbarStrings("Rede: Crownlink", "AO VIVO"),
        vault = VaultStrings(
            "SALDO TOTAL DO COFRE", "Enviar", "Receber", "PORTFÓLIO", "Seus ativos",
            "Gerenciar", "Gerar rendimento", "Até 14.2% APY", "Benefícios Vault+", "3 novos benefícios"
        ),
        markets = MarketsStrings(
            "FEED DE INTELIGÊNCIA", "Pulso do mercado", "Sinais em tempo real da rede Crownlink.",
            "TOP ALTA", "Neon Index", "Sintéticos globais · 24H", "adicionado à sua watchlist",
            "Resumo do mercado", "Trading ativo", "Comprar", "Vender", "Quantidade", "Tipo de ordem",
            "Mercado", "Limite", "Enviar ordem", "Gráfico de preço", "Selecionar ativo"
        ),
        staking = StakingStrings(
            "RENDIMENTO PASSIVO", "Stake e cresça", "Coloque seus ativos para trabalhar na economia quântica.",
            "Total em stake", "+$18.42 ganhos esta semana", "Comece a ganhar hoje", "APY atual",
            "Período de bloqueio", "Flexível", "Taxa de rede", "Staking ativo", "Stake CRWN",
            "Protegido por Crownlink", "Ativos são protegidos por validação multicamada e um protocolo auditado."
        ),
        swap = SwapStrings(
            "TROCA INSTANTÂNEA", "Quantum swap", "Troque dentro do cofre. Sem atrito, sem ruído.",
            "VOCÊ PAGA", "VOCÊ RECEBE", "Saldo", "Melhor rota", "Revisar swap",
            "Desenvolvido por", "CROWNLINK ROUTER"
        ),
        card = CardStrings(
            "O FUTURO DO GASTO", "Quantum Card", "Um cartão. Cada dimensão.",
            "Cartão pronto", "Cartão virtual · termina em 2389", "Detalhes", "Bloquear cartão", "Desbloquear cartão",
            "GASTOS RECENTES", "Atividade", "Ver tudo", "Orbital Market", "Hoje, 10:42",
            "Recompensa cashback", "Ontem, 18:20"
        ),
        plus = PlusStrings(
            "A ATUALIZAÇÃO", "Vault", "Desbloqueie o espectro completo da sua riqueza.",
            "Teste grátis", "Liquidação instantânea", "Mova ativos em menos de 2 segundos",
            "2× recompensas", "Ganhe mais em cada gasto no cartão", "Roteamento prioritário",
            "Acesse as rotas mais rápidas do mercado", "Curado para a próxima era", "Junte-se a 18.402 membros early"
        ),
        system = SystemStrings(
            "CONFIGURAÇÕES PRINCIPAIS", "Sistema", "Seu cofre, sintonizado à sua frequência.",
            "Centro de segurança", "Todos os sistemas nominais", "Aparência", "Meia-noite / Neon",
            "Preferência de rede", "Crownlink · 12 ms", "Notificações", "Alertas inteligentes ativados",
            "Verificar segurança", "Verificando…", "CROWN VAULT OS", "Idioma", "Português",
            "Aparência", "Meia-noite", "Neon", "Auto", "Preferência de rede",
            "Crownlink · 12 ms", "Quantum Mesh · 28 ms", "Direct Link · 45 ms",
            "Notificações", "Alertas inteligentes", "Alertas de transação com IA",
            "Alertas de preço", "Notificar movimentos de preço significativos",
            "Alertas de segurança", "Eventos de segurança críticos", "Notificações push ativadas",
            "Tema aplicado", "Rede alterada com sucesso", "Configurações de notificações salvas"
        ),
        security = SecurityStrings(
            "CENTRO DE PROTEÇÃO", "Segurança", "Seu cofre, fortificado em cada camada.",
            "Bloqueio biométrico", "Face ID · Ativado", "Autenticação de dois fatores", "Autenticador ativo",
            "Verificação de transações", "Monitoramento em tempo real", "Endereços confiáveis", "3 endereços verificados",
            "Verificação completa de segurança", "Todos os sistemas nominais",
            "Última verificação: agora · 0 ameaças detectadas em todas as camadas.",
            "Verificando…", "Sistemas nominais", "Zero ameaças detectadas", "Verificando todas as camadas de segurança…"
        ),
        profile = ProfileStrings(
            "SUA IDENTIDADE", "Perfil", "Gerencie sua identidade Crown Vault.",
            "Nome de exibição", "Status de verificação", "Verificado · Nível 3",
            "Endereço da carteira", "Exportar dados do perfil"
        ),
        drawer = DrawerStrings(
            "Configurações", "Preferências do app", "Segurança", "Centro de proteção",
            "Notificações", "Caixa de alertas", "Perfil", "Traveler 07 · Verificado",
            "SALDO DO COFRE", "Legal"
        ),
        modal = ModalStrings(
            "CAIXA DE ENTRADA", "Notificações", "Você está em dia", "Nova atividade aparecerá aqui.",
            "TRANSFERÊNCIA SAÍDA", "TRANSFERÊNCIA ENTRADA", "Enviar ativos", "Receber ativos",
            "Cole um endereço de carteira", "Continuar", "Copiar endereço",
            "Transferência preparada para revisão", "Endereço copiado para a área de transferência"
        ),
        legal = LegalStrings(
            terms = LegalContent(
                "Termos e Condições",
                listOf(
                    "Ao acessar e usar o Crown Vault, você concorda em estar sujeito a estes Termos e Condições. A Crown Tech™ fornece a plataforma como uma ferramenta de gestão de ativos digitais.",
                    "O uso do aplicativo implica que você é maior de idade e responsável por suas chaves privadas, transações e decisões financeiras. A Crown Tech™ não é responsável por perdas decorrentes do uso indevido da plataforma.",
                    "A plataforma pode ser atualizada, modificada ou descontinuada a qualquer momento sem aviso prévio. O uso continuado constitui aceitação de quaisquer alterações."
                )
            ),
            privacy = LegalContent(
                "Política de Privacidade",
                listOf(
                    "O Crown Vault prioriza sua privacidade. Não coletamos dados pessoais desnecessários nem vendemos suas informações a terceiros.",
                    "Os dados da sua carteira, transações e preferências são armazenados de forma criptografada e local quando possível. As transações são processadas através da rede Crownlink sem intermediários.",
                    "Podemos coletar métricas anônimas de uso para melhorar a experiência. Essas métricas não permitem identificação pessoal."
                )
            ),
            usage = LegalContent(
                "Termos de Uso",
                listOf(
                    "O Crown Vault é projetado como uma ferramenta de gestão de criptoativos. O uso do aplicativo é por sua conta e risco.",
                    "Não é permitido usar a plataforma para atividades ilícitas, lavagem de dinheiro ou qualquer ação que viole as leis aplicáveis em sua jurisdição.",
                    "Os recursos de staking, swap e cartão podem estar sujeitos a disponibilidade regional. A Crown Tech™ reserva-se o direito de limitar ou suspender o acesso em caso de uso indevido."
                )
            ),
            disclaimer = LegalContent(
                "Aviso Legal",
                listOf(
                    "O Crown Vault é um aplicativo de demonstração para fins educacionais e de visualização. Os saldos, ativos e transações exibidos são simulados e não representam ativos financeiros reais.",
                    "A Crown Tech™ não oferece aconselhamento financeiro. Criptomoedas e ativos digitais apresentam riscos significativos, incluindo perda total de capital. Consulte um consultor profissional antes de tomar decisões de investimento.",
                    "Este aplicativo não constitui uma oferta de serviços financeiros regulamentados. Todas as marcas, logotipos e designs são propriedade da Crown Tech™."
                )
            )
        ),
        common = CommonStrings("Verificado", "AGORA", ZERO_KNOWLEDGE_BADGE),
        vaultPlus = VaultPlusStrings(
            "Desbloquear Vault+", "Teste Gratuito de 7 Dias", "Mensal", "Anual", "Economize 25%", "/mês", "/ano",
            "Liquidação Instantânea", "Transferências de ativos sem atraso na rede Crownlink.",
            "2x Recompensas de Staking", "Rendimento aumentado em todos os ativos em stake (CRWN, ETH, BTC).",
            "Roteamento Prioritario", "Acesso a rotas líquidas de alta velocidade com taxas menores.",
            "Escudo de Segurança Avançado", "Criptografia multicamada do cofre e detecção de ameaças em tempo real.",
            "Cartão Virtual Quântico", "Criação ilimitada de cartões virtuais sem taxas FX.",
            "Iniciar Teste Gratuito de 7 Dias ($9.99/mês depois)", "Cancele quando quiser nas configurações. Protegido pelo Crownlink Protocol.",
            "Vault+ Ativo", FREEMIUM_MESSAGE, FREE_TIER_NOTE
        ),
        toast = ToastStrings(
            "CRWN agora está gerando rendimento", "Cotação atualizada", "Teste do Vault+ iniciado",
            "Detalhes do cartão copiados", "Cartão bloqueado temporariamente", "Cartão desbloqueado",
            "Verificação de segurança completa — sem problemas", "Perfil exportado com sucesso",
            "Bem-vindo ao Vault+! Teste iniciado com sucesso", "Ordem enviada com sucesso"
        )
    ),
    Lang.ZH to TranslationSet(
        nav = NavStrings("金库", "市场", "质押", "兑换", "卡片", "Vault+", "系统"),
        topbar = TopbarStrings("网络: Crownlink", "实时"),
        vault = VaultStrings(
            "金库总余额", "发送", "接收", "投资组合", "您的资产",
            "管理", "赚取收益", "最高 14.2% APY", "Vault+ 特权", "3 项新福利"
        ),
        markets = MarketsStrings(
            "情报推送", "市场脉搏", "来自 Crownlink 网络的实时信号。",
            "涨幅榜首", "Neon Index", "全球合成资产 · 24小时", "已添加到您的关注列表",
            "市场概览", "主动交易", "买入", "卖出", "订单数量", "订单类型",
            "市价", "限价", "下单", "价格图表", "选择交易资产"
        ),
        staking = StakingStrings(
            "被动收益", "质押增值", "让您的资产在量子经济中运转。",
            "质押总量", "本周赚取 +$18.42", "今天开始赚取", "当前 APY",
            "锁定期", "灵活", "网络费", "质押已激活", "质押 CRWN",
            "由 Crownlink 保护", "资产通过多层验证和审计协议保护。"
        ),
        swap = SwapStrings(
            "即时兑换", "量子兑换", "在金库内兑换。无摩擦，无噪音。",
            "您支付", "您接收", "余额", "最佳路径", "查看兑换",
            "技术支持", "CROWNLINK 路由"
        ),
        card = CardStrings(
            "消费的未来", "量子卡", "一张卡。每个维度。",
            "卡片就绪", "虚拟卡 · 尾号 2389", "详情", "锁定卡片", "解锁卡片",
            "近期消费", "活动", "查看全部", "Orbital Market", "今天, 10:42",
            "现金返还奖励", "昨天, 18:20"
        ),
        plus = PlusStrings(
            "升级", "Vault", "解锁您财富的全部光谱。",
            "免费试用", "即时结算", "2秒内移动资产",
            "2倍奖励", "每次刷卡赚取更多", "优先路由",
            "访问最快的市场路径", "为下一个时代精心打造", "加入 18,402 名早期成员"
        ),
        system = SystemStrings(
            "核心设置", "系统", "您的金库，调至您的频率。",
            "安全中心", "所有系统正常", "外观", "午夜 / 霓虹",
            "网络偏好", "Crownlink · 12 毫秒", "通知", "智能提醒已启用",
            "运行安全扫描", "扫描中…", "CROWN VAULT OS", "语言", "中文",
            "外观", "午夜", "霓虹", "自动", "网络偏好",
            "Crownlink · 12 毫秒", "Quantum Mesh · 28 毫秒", "Direct Link · 45 毫秒",
            "通知", "智能提醒", "AI驱动的交易提醒",
            "价格提醒", "价格大幅波动时通知",
            "安全提醒", "关键安全事件", "推送通知已启用",
            "主题已应用", "网络切换成功", "通知设置已保存"
        ),
        security = SecurityStrings(
            "保护中心", "安全", "您的金库，层层加固。",
            "生物识别锁", "面容 ID · 已启用", "双因素认证", "认证器已激活",
            "交易扫描", "实时监控", "白名单地址", "3 个可信地址",
            "运行全面安全扫描", "所有系统正常",
            "上次扫描：刚刚 · 所有层未检测到威胁。",
            "扫描中…", "系统正常", "未检测到威胁", "正在扫描所有安全层…"
        ),
        profile = ProfileStrings(
            "您的身份", "个人资料", "管理您的 Crown Vault 身份。",
            "显示名称", "验证状态", "已验证 · 第3级",
            "钱包地址", "导出个人资料数据"
        ),
        drawer = DrawerStrings(
            "设置", "应用偏好", "安全", "保护中心",
            "通知", "提醒收件箱", "个人资料", "Traveler 07 · 已验证",
            "金库余额", "法律"
        ),
        modal = ModalStrings(
            "收件箱", "通知", "您已全部查看完毕", "新活动将显示在此处。",
            "转出", "转入", "发送资产", "接收资产",
            "粘贴钱包地址", "继续", "复制地址",
            "转账已准备待审核", "地址已复制到剪贴板"
        ),
        legal = LegalStrings(
            terms = LegalContent(
                "条款与条件",
                listOf(
                    "访问和使用 Crown Vault 即表示您同意受这些条款与条件的约束。Crown Tech™ 将该平台作为数字资产管理工具提供。",
                    "使用本应用程序即表示您已达到法定年龄，并对您的私钥、交易和财务决定负责。Crown Tech™ 不对因不当使用平台而造成的损失承担责任。",
                    "平台可能随时更新、修改或停止，恕不另行通知。继续使用即表示接受任何变更。"
                )
            ),
            privacy = LegalContent(
                "隐私政策",
                listOf(
                    "Crown Vault 优先保护您的隐私。我们不收集不必要的个人数据，也不会将您的信息出售给第三方。",
                    "您的钱包数据、交易和偏好设置在可能的情况下以加密方式在本地存储。交易通过 Crownlink 网络处理，无需中介。",
                    "我们可能会收集匿名使用指标以改善体验。这些指标不允许个人身份识别。"
                )
            ),
            usage = LegalContent(
                "使用条款",
                listOf(
                    "Crown Vault 设计为加密资产管理工具。使用本应用程序的风险由您自行承担。",
                    "不得使用本平台进行非法活动、洗钱或任何违反您所在司法管辖区适用法律的行为。",
                    "质押、兑换和卡片功能可能受区域可用性限制。Crown Tech™ 保留在不当使用情况下限制或暂停访问的权利。"
                )
            ),
            disclaimer = LegalContent(
                "免责声明",
                listOf(
                    "Crown Vault 是一个用于教育和可视化目的的演示应用程序。所显示的余额、资产和交易均为模拟数据，不代表真实的金融资产。",
                    "Crown Tech™ 不提供财务建议。加密货币和数字资产具有重大风险，包括资本全损。在做出投资决定之前，请咨询专业顾问。",
                    "本应用程序不构成受监管金融服务的要约。所有商标、标志和设计均为 Crown Tech™ 的财产。"
                )
            )
        ),
        common = CommonStrings("已验证", "现在", ZERO_KNOWLEDGE_BADGE),
        vaultPlus = VaultPlusStrings(
            "解锁 Vault+", "7天免费试用", "每月", "每年", "节省 25%", "/月", "/年",
            "即时结算", "在 Crownlink 网络上零延迟转移资产。",
            "2倍质押奖励", "所有质押资产（CRWN、ETH、BTC）收益翻倍。",
            "优先路由", "访问高速流动性路径，降低网络费用。",
            "高级安全护盾", "多层金库加密和实时威胁检测。",
            "量子虚拟卡", "无限创建虚拟卡，零外汇手续费。",
            "开始7天免费试用（之后 $9.99/月）", "可在设置中随时取消。由 Crownlink Protocol 保护。",
            "Vault+ 已激活", FREEMIUM_MESSAGE, FREE_TIER_NOTE
        ),
        toast = ToastStrings(
            "CRWN 正在产生收益", "汇率已更新", "Vault+ 试用已开始",
            "虚拟卡详情已复制", "卡片已临时锁定", "卡片已解锁",
            "安全扫描完成 — 未发现问题", "个人资料导出成功",
            "欢迎加入 Vault+！试用已成功开启", "订单下单成功"
        )
    ),
    Lang.JA to TranslationSet(
        nav = NavStrings("金庫", "市場", "ステーキング", "スワップ", "カード", "Vault+", "システム"),
        topbar = TopbarStrings("ネットワーク: Crownlink", "ライブ"),
        vault = VaultStrings(
            "金庫の総残高", "送信", "受信", "ポートフォリオ", "あなたの資産",
            "管理", "利回りを獲得", "最大 14.2% APY", "Vault+ 特典", "3つの新特典"
        ),
        markets = MarketsStrings(
            "インテリジェンスフィード", "市場パルス", "Crownlink ネットワークからのリアルタイムシグナル。",
            "トップムーバー", "Neon Index", "グローバル合成 · 24H", "ウォッチリストに追加しました",
            "市場概況", "アクティブ取引", "購入", "売却", "注文数量", "注文タイプ",
            "成行", "指値", "注文を出す", "価格チャート", "取引資産を選択"
        ),
        staking = StakingStrings(
            "パッシブ収益", "ステークして成長", "量子経済で資産を活用しましょう。",
            "ステーク総量", "今週 +$18.42 獲得", "今日から始めよう", "現在のAPY",
            "ロック期間", "フレキシブル", "ネットワーク手数料", "ステーキング中", "CRWNをステーク",
            "Crownlink で保護", "資産は多層検証と監査済みプロトコルで保護されています。"
        ),
        swap = SwapStrings(
            "インスタント交換", "クアンタムスワップ", "金庫内で交換。摩擦なし、ノイズなし。",
            "支払い", "受取", "残高", "最適ルート", "スワップを確認",
            "提供元", "CROWNLINK ルーター"
        ),
        card = CardStrings(
            "消費の未来", "クアンタムカード", "一枚のカード。全次元。",
            "カード準備完了", "バーチャルカード · 末尾 2389", "詳細", "カードをロック", "カードのロック解除",
            "最近の支出", "アクティビティ", "すべて見る", "Orbital Market", "今日, 10:42",
            "キャッシュバック報酬", "昨日, 18:20"
        ),
        plus = PlusStrings(
            "アップグレード", "Vault", "富の全スペクトラムを解放。",
            "無料トライアル", "即時決済", "2秒以内に資産を移動",
            "2倍報酬", "カード利用ごとに多く獲得", "優先ルーティング",
            "最速の市場パスにアクセス", "次の時代のために厳選", "18,402人の早期メンバーに参加"
        ),
        system = SystemStrings(
            "コア設定", "システム", "あなたの金庫、あなたの周波数に調整。",
            "セキュリティセンター", "全システム正常", "外観", "ミッドナイト / ネオン",
            "ネットワーク設定", "Crownlink · 12ms", "通知", "スマートアラート有効",
            "セキュリティスキャン", "スキャン中…", "CROWN VAULT OS", "言語", "日本語",
            "外観", "ミッドナイト", "ネオン", "自動", "ネットワーク設定",
            "Crownlink · 12ms", "Quantum Mesh · 28ms", "Direct Link · 45ms",
            "通知", "スマートアラート", "AI搭載取引アラート",
            "価格アラート", "重大な価格変動を通知",
            "セキュリティアラート", "重要なセキュリティイベント", "プッシュ通知有効",
            "テーマを適用しました", "ネットワークを切り替えました", "通知設定を保存しました"
        ),
        security = SecurityStrings(
            "プロテクションセンター", "セキュリティ", "あなたの金庫、すべての層で強化。",
            "生体認証ロック", "Face ID · 有効", "二要素認証", "認証アプリアクティブ",
            "取引スキャン", "リアルタイム監視", "ホワイトリストアドレス", "3件の信頼済みアドレス",
            "完全セキュリティスキャン", "全システム正常",
            "前回スキャン: たった今 · 全層で0件の脅威を検出。",
            "スキャン中…", "システム正常", "脅威ゼロ", "すべてのセキュリティ層をスキャン中…"
        ),
        profile = ProfileStrings(
            "あなたのアイデンティティ", "プロフィール", "Crown Vault のアイデンティティを管理。",
            "表示名", "認証ステータス", "認証済み · Tier 3",
            "ウォレットアドレス", "プロフィールデータをエクスポート"
        ),
        drawer = DrawerStrings(
            "設定", "アプリの設定", "セキュリティ", "保護センター",
            "通知", "アラート受信箱", "プロフィール", "Traveler 07 · 認証済み",
            "金庫残高", "法的事項"
        ),
        modal = ModalStrings(
            "受信箱", "通知", "すべて確認済みです", "新しいアクティビティがここに表示されます。",
            "送信", "受信", "資産を送信", "資産を受信",
            "ウォレットアドレスを貼り付け", "続行", "アドレスをコピー",
            "送金準備完了", "アドレスをクリップボードにコピーしました"
        ),
        legal = LegalStrings(
            terms = LegalContent(
                "利用規約",
                listOf(
                    "Crown Vault にアクセスして使用することにより、これらの利用規約に同意したことになります。Crown Tech™ はプラットフォームをデジタル資産管理ツールとして提供します。",
                    "アプリケーションの使用は、あなたが法定年齢に達しており、秘密鍵、取引、財務上の決定に責任を持つことを意味します。Crown Tech™ はプラットフォームの不適切な使用から生じる損失について責任を負いません。",
                    "プラットフォームは事前通知なく更新、変更、または中止される場合があります。継続使用により変更を受け入れたことになります。"
                )
            ),
            privacy = LegalContent(
                "プライバシーポリシー",
                listOf(
                    "Crown Vault はプライバシーを優先します。不要な個人データを収集せず、第三者に情報を販売しません。",
                    "ウォレットデータ、取引、設定は可能な限り暗号化されてローカルに保存されます。取引は仲介者なしで Crownlink ネットワークを通じて処理されます。",
                    "体験を向上させるため匿名の使用メトリクスを収集する場合があります。これらのメトリクスでは個人を特定できません。"
                )
            ),
            usage = LegalContent(
                "利用条件",
                listOf(
                    "Crown Vault は暗号資産管理ツールとして設計されています。アプリケーションの使用は自己責任です。",
                    "違法活動、マネーロンダリング、または管轄区域の適用法に違反する行為にプラットフォームを使用することは許可されていません。",
                    "ステーキング、スワップ、カード機能は地域によって利用できない場合があります。Crown Tech™ は不適切な使用の場合、アクセスを制限または停止する権利を留保します。"
                )
            ),
            disclaimer = LegalContent(
                "免責事項",
                listOf(
                    "Crown Vault は教育および視覚化を目的としたデモアプリケーションです。表示される残高、資産、取引はシミュレートされており、実際の金融資産を表すものではありません。",
                    "Crown Tech™ は財務アドバイスを提供しません。暗号通貨とデジタル資産には資本の完全な損失を含む重大なリスクがあります。投資決定を下す前に専門のアドバイザーに相談してください。",
                    "このアプリケーションは規制された金融サービスの提供を構成するものではありません。すべての商標、ロゴ、デザインは Crown Tech™ の財産です。"
                )
            )
        ),
        common = CommonStrings("認証済み", "現在", ZERO_KNOWLEDGE_BADGE),
        vaultPlus = VaultPlusStrings(
            "Vault+をアンロック", "7日間無料トライアル", "月額", "年額", "25%お得", "/月", "/年",
            "即時決済", "Crownlinkネットワークで遅延ゼロの資産移転。",
            "2倍ステーキング報酬", "すべてのステーク資産（CRWN、ETH、BTC）の利回り向上。",
            "優先ルーティング", "低ネットワーク手数料の高速流動ルートへアクセス。",
            "高度なセキュリティシールド", "多層ボルト暗号化とリアルタイム脅威検出。",
            "クアンタム仮想カード", "無制限の仮想カード作成、FX手数料ゼロ。",
            "7日間無料トライアルを開始（以降 $9.99/月）", "設定からいつでもキャンセル可能。Crownlink Protocol で保護。",
            "Vault+ アクティブ", FREEMIUM_MESSAGE, FREE_TIER_NOTE
        ),
        toast = ToastStrings(
            "CRWN が利回りを生成中です", "レートが更新されました", "Vault+ の試用が開始されました",
            "バーチャルカードの詳細をコピーしました", "カードを一時的にロックしました", "カードのロックを解除しました",
            "セキュリティスキャン完了 — 問題なし", "プロフィールのエクスポートに成功しました",
            "Vault+へようこそ！トライアルが正常に開始されました", "注文が正常に発注されました"
        )
    ),
    Lang.KO to TranslationSet(
        nav = NavStrings("볼트", "시장", "스테이킹", "스왑", "카드", "Vault+", "시스템"),
        topbar = TopbarStrings("네트워크: Crownlink", "실시간"),
        vault = VaultStrings(
            "볼트 총 잔액", "보내기", "받기", "포트폴리오", "보유 자산",
            "관리", "수익 창출", "최대 14.2% APY", "Vault+ 혜택", "3개의 새로운 혜택"
        ),
        markets = MarketsStrings(
            "인텔리전스 피드", "시장 맥박", "Crownlink 네트워크의 실시간 신호.",
            "최대 상승", "Neon Index", "글로벌 합성 자산 · 24H", "관심 목록에 추가되었습니다",
            "시장 개요", "활성 거래", "매수", "매도", "주문 수량", "주문 유형",
            "시장가", "지정가", "주문하기", "가격 차트", "거래 자산 선택"
        ),
        staking = StakingStrings(
            "패시브 수익", "스테이킹하여 성장", "양자 경제에서 자산을 활용하세요.",
            "총 스테이킹", "이번 주 +$18.42 획득", "오늘 시작하세요", "현재 APY",
            "잠금 기간", "유연함", "네트워크 수수료", "스테이킹 활성", "CRWN 스테이킹",
            "Crownlink으로 보호", "자산은 다층 검증과 감사된 프로토콜로 보호됩니다."
        ),
        swap = SwapStrings(
            "즉시 교환", "퀀텀 스왑", "볼트 내에서 교환. 마찰 없음, 소음 없음.",
            "지불", "수령", "잔액", "최적 경로", "스왑 검토",
            "제공", "CROWNLINK 라우터"
        ),
        card = CardStrings(
            "소비의 미래", "퀀텀 카드", "하나의 카드. 모든 차원.",
            "카드 준비됨", "가상 카드 · 끝번호 2389", "상세", "카드 잠금", "카드 잠금 해제",
            "최근 지출", "활동", "전체 보기", "Orbital Market", "오늘, 10:42",
            "캐시백 보상", "어제, 18:20"
        ),
        plus = PlusStrings(
            "업그레이드", "Vault", "부의 전체 스펙트럼을 잠금 해제.",
            "무료 체험", "즉시 정산", "2초 이내 자산 이동",
            "2배 보상", "카드 사용마다 더 많이 획득", "우선 라우팅",
            "가장 빠른 시장 경로에 접근", "다음 시대를 위해 엄선", "18,402명의 얼리 멤버 참여"
        ),
        system = SystemStrings(
            "핵심 설정", "시스템", "당신의 볼트, 당신의 주파수에 맞춰.",
            "보안 센터", "모든 시스템 정상", "외관", "미드나이트 / 네온",
            "네트워크 설정", "Crownlink · 12ms", "알림", "스마트 알림 활성화",
            "보안 스캔", "스캔 중…", "CROWN VAULT OS", "언어", "한국어",
            "외관", "미드나이트", "네온", "자동", "네트워크 설정",
            "Crownlink · 12ms", "Quantum Mesh · 28ms", "Direct Link · 45ms",
            "알림", "스마트 알림", "AI 기반 거래 알림",
            "가격 알림", "중요 가격 변동 알림",
            "보안 알림", "중요 보안 이벤트", "푸시 알림 활성화",
            "테마 적용됨", "네트워크 전환 완료", "알림 설정 저장됨"
        ),
        security = SecurityStrings(
            "보호 센터", "보안", "당신의 볼트, 모든 층에서 강화.",
            "생체 인식 잠금", "Face ID · 활성화", "이중 인증", "인증기 활성",
            "거래 스캔", "실시간 모니터링", "화이트리스트 주소", "3개 신뢰 주소",
            "전체 보안 스캔", "모든 시스템 정상",
            "마지막 스캔: 방금 · 모든 층에서 위협 0건 감지.",
            "스캔 중…", "시스템 정상", "위협 0건 감지", "모든 보안 계층 스캔 중…"
        ),
        profile = ProfileStrings(
            "당신의 정체성", "프로필", "Crown Vault 정체성 관리.",
            "표시 이름", "인증 상태", "인증됨 · Tier 3",
            "지갑 주소", "프로필 데이터 내보내기"
        ),
        drawer = DrawerStrings(
            "설정", "앱 환경설정", "보안", "보호 센터",
            "알림", "알림 수신함", "프로필", "Traveler 07 · 인증됨",
            "볼트 잔액", "법적"
        ),
        modal = ModalStrings(
            "수신함", "알림", "모두 확인했습니다", "새 활동이 여기에 표시됩니다.",
            "송금", "수금", "자산 보내기", "자산 받기",
            "지갑 주소 붙여넣기", "계속", "주소 복사",
            "송금 검토 준비됨", "주소가 클립보드에 복사됨"
        ),
        legal = LegalStrings(
            terms = LegalContent(
                "이용약관",
                listOf(
                    "Crown Vault에 접속하고 사용함으로써 이 이용약관에 구속되는 것에 동의합니다. Crown Tech™은 플랫폼을 디지털 자산 관리 도구로 제공합니다.",
                    "애플리케이션 사용은 귀하가 성년이며 개인 키, 거래 및 재무 결정에 대해 책임이 있음을 의미합니다. Crown Tech™은 플랫폼의 부적절한 사용으로 인한 손실에 대해 책임지지 않습니다.",
                    "플랫폼은 사전 통지 없이 업데이트, 수정 또는 중단될 수 있습니다. 계속 사용하면 변경 사항을 수락하는 것으로 간주됩니다."
                )
            ),
            privacy = LegalContent(
                "개인정보 처리방침",
                listOf(
                    "Crown Vault은 귀하의 개인정보를 우선시합니다. 불필요한 개인 데이터를 수집하지 않으며 귀하의 정보를 제3자에게 판매하지 않습니다.",
                    "지갑 데이터, 거래 및 환경설정은 가능한 한 암호화되어 로컬에 저장됩니다. 거래는 중개자 없이 Crownlink 네트워크를 통해 처리됩니다.",
                    "경험을 개선하기 위해 익명 사용 통계를 수집할 수 있습니다. 이 통계로는 개인을 식별할 수 없습니다."
                )
            ),
            usage = LegalContent(
                "이용 조건",
                listOf(
                    "Crown Vault은 암호 자산 관리 도구로 설계되었습니다. 애플리케이션 사용은 본인 책임입니다.",
                    "불법 활동, 자금 세탁 또는 관할 지역의 적용 법률을 위반하는 행위에 플랫폼을 사용하는 것은 허용되지 않습니다.",
                    "스테이킹, 스왑 및 카드 기능은 지역 가용성의 적용을 받을 수 있습니다. Crown Tech™은 부적절한 사용 시 접근을 제한하거나 중단할 권리가 있습니다."
                )
            ),
            disclaimer = LegalContent(
                "면책 조항",
                listOf(
                    "Crown Vault은 교육 및 시각화 목적의 데모 애플리케이션입니다. 표시되는 잔액, 자산 및 거래는 시뮬레이션된 것이며 실제 금융 자산을 나타내지 않습니다.",
                    "Crown Tech™은 재무 조언을 제공하지 않습니다. 암호화폐와 디지털 자산은 자본 전체 손실을 포함한 중대한 위험을 수반합니다. 투자 결정을 내리기 전에 전문 자문가에게 문의하세요.",
                    "이 애플리케이션은 규제된 금융 서비스 제공을 구성하지 않습니다. 모든 상표, 로고 및 디자인은 Crown Tech™의 재산입니다."
                )
            )
        ),
        common = CommonStrings("인증됨", "현재", ZERO_KNOWLEDGE_BADGE),
        vaultPlus = VaultPlusStrings(
            "Vault+ 잠금 해제", "7일 무료 체험", "월간", "연간", "25% 절약", "/월", "/년",
            "즉시 정산", "Crownlink 네트워크에서 지연 없는 자산 이체.",
            "2배 스테이킹 보상", "모든 스테이킹 자산(CRWN, ETH, BTC) 수익 증가.",
            "우선 라우팅", "낮은 네트워크 수수료로 고속 유동성 경로 접근.",
            "고급 보안 실드", "다층 볼트 암호화 및 실시간 위협 탐지.",
            "퀀텀 가상 카드", "무제한 가상 카드 생성, FX 수수료 제로.",
            "7일 무료 체험 시작 (이후 $9.99/월)", "설정에서 언제든 취소 가능. Crownlink Protocol로 보호됨.",
            "Vault+ 활성", FREEMIUM_MESSAGE, FREE_TIER_NOTE
        ),
        toast = ToastStrings(
            "CRWN이 수익을 창출 중입니다", "환율이 업데이트되었습니다", "Vault+ 체험이 시작되었습니다",
            "가상 카드 정보가 복사되었습니다", "카드가 일시적으로 잠겼습니다", "카드 잠금 해제됨",
            "보안 스캔 완료 — 문제 없음", "프로필 내보내기 성공",
            "Vault+에 오신 것을 환영합니다! 체험이 성공적으로 시작되었습니다", "주문이 성공적으로 접수되었습니다"
        )
    ),
    Lang.DE to TranslationSet(
        nav = NavStrings("Tresor", "Märkte", "Staking", "Swap", "Karte", "Vault+", "System"),
        topbar = TopbarStrings("Netzwerk: Crownlink", "LIVE"),
        vault = VaultStrings(
            "GESAMTTRESOR-BESTAND", "Senden", "Empfangen", "PORTFOLIO", "Ihre Vermögenswerte",
            "Verwalten", "Rendite erzielen", "Bis zu 14,2% APY", "Vault+ Vorteile", "3 neue Vorteile"
        ),
        markets = MarketsStrings(
            "INTELLIGENZ-FEED", "Marktpuls", "Echtzeit-Signale aus dem Crownlink-Netzwerk.",
            "TOP GEWINNER", "Neon Index", "Globale Synthetika · 24H", "zu Ihrer Watchlist hinzugefügt",
            "Marktübersicht", "Aktives Trading", "Kaufen", "Verkaufen", "Auftragsmenge", "Auftragstyp",
            "Markt", "Limit", "Auftrag platzieren", "Preischart", "Vermögenswert wählen"
        ),
        staking = StakingStrings(
            "PASSIVES EINKOMMEN", "Staken & wachsen", "Bringen Sie Ihre Vermögenswerte in der Quantenökonomie zum Arbeiten.",
            "Gesamt gestakt", "+$18.42 diese Woche verdient", "Heute beginnen", "Aktueller APY",
            "Sperrfrist", "Flexibel", "Netzwerkgebühr", "Staking aktiv", "CRWN staken",
            "Geschützt durch Crownlink", "Vermögenswerte sind durch mehrschichtige Validierung und ein geprüftes Protokoll gesichert."
        ),
        swap = SwapStrings(
            "SOFORTIGER TAUSCH", "Quantum-Swap", "Tauschen Sie innerhalb des Tresors. Keine Reibung, kein Lärm.",
            "SIE ZAHLEN", "SIE ERHALTEN", "Guthaben", "Beste Route", "Swap prüfen",
            "Unterstützt von", "CROWNLINK ROUTER"
        ),
        card = CardStrings(
            "DIE ZUKUNFT DES BEZAHLENS", "Quantum-Karte", "Eine Karte. Jede Dimension.",
            "Karte bereit", "Virtuelle Karte · endet auf 2389", "Details", "Karte sperren", "Karte entsperren",
            "LETZTE AUSGABEN", "Aktivität", "Alle ansehen", "Orbital Market", "Heute, 10:42",
            "Cashback-Belohnung", "Gestern, 18:20"
        ),
        plus = PlusStrings(
            "DAS UPGRADE", "Vault", "Schalten Sie das volle Spektrum Ihres Vermögens frei.",
            "Kostenlos testen", "Sofortige Abwicklung", "Vermögenswerte in unter 2 Sekunden bewegen",
            "2× Belohnungen", "Mehr bei jeder Kartenzahlung verdienen", "Priorisiertes Routing",
            "Zugang zu den schnellsten Marktrouten", "Kuratiert für die nächste Ära", "Treten Sie 18.402 Early-Mitgliedern bei"
        ),
        system = SystemStrings(
            "KERN-EINSTELLUNGEN", "System", "Ihr Tresor, auf Ihre Frequenz abgestimmt.",
            "Sicherheitszentrum", "Alle Systeme nominal", "Erscheinungsbild", "Mitternacht / Neon",
            "Netzwerk-Einstellung", "Crownlink · 12 ms", "Benachrichtigungen", "Smart-Alerts aktiviert",
            "Sicherheits-Scan", "Scannen…", "CROWN VAULT OS", "Sprache", "Deutsch",
            "Erscheinungsbild", "Mitternacht", "Neon", "Auto", "Netzwerk-Einstellung",
            "Crownlink · 12 ms", "Quantum Mesh · 28 ms", "Direct Link · 45 ms",
            "Benachrichtigungen", "Smart-Alerts", "KI-gestützte Transaktionswarnungen",
            "Preis-Alerts", "Bei signifikanten Preisbewegungen benachrichtigen",
            "Sicherheits-Alerts", "Kritische Sicherheitsereignisse", "Push-Benachrichtigungen aktiviert",
            "Design angewendet", "Netzwerk erfolgreich gewechselt", "Benachrichtigungseinstellungen gespeichert"
        ),
        security = SecurityStrings(
            "SCHUTZ-ZENTRUM", "Sicherheit", "Ihr Tresor, auf jeder Ebene befestigt.",
            "Biometrische Sperre", "Face ID · Aktiviert", "Zwei-Faktor-Authentifizierung", "Authenticator aktiv",
            "Transaktions-Scanning", "Echtzeit-Überwachung", "Whitelist-Adressen", "3 vertrauenswürdige Adressen",
            "Vollständigen Sicherheits-Scan ausführen", "Alle Systeme nominal",
            "Letzter Scan: gerade eben · 0 Bedrohungen auf allen Ebenen erkannt.",
            "Scannen…", "Systeme nominal", "Null Bedrohungen erkannt", "Alle Sicherheitsebenen werden gescannt…"
        ),
        profile = ProfileStrings(
            "Ihre Identität", "Profil", "Verwalten Sie Ihre Crown Vault-Identität.",
            "Anzeigename", "Verifizierungsstatus", "Verifiziert · Stufe 3",
            "Wallet-Adresse", "Profildaten exportieren"
        ),
        drawer = DrawerStrings(
            "Einstellungen", "App-Einstellungen", "Sicherheit", "Schutzzentrum",
            "Benachrichtigungen", "Benachrichtigungs-Posteingang", "Profil", "Traveler 07 · Verifiziert",
            "TRESOR-BESTAND", "Rechtliches"
        ),
        modal = ModalStrings(
            "POSTEINGANG", "Benachrichtigungen", "Sie sind auf dem neuesten Stand", "Neue Aktivität wird hier angezeigt.",
            "AUSGANGSÜBERWEISUNG", "EINGANGSÜBERWEISUNG", "Vermögenswerte senden", "Vermögenswerte empfangen",
            "Wallet-Adresse einfügen", "Weiter", "Adresse kopieren",
            "Überweisung zur Prüfung vorbereitet", "Adresse in Zwischenablage kopiert"
        ),
        legal = LegalStrings(
            terms = LegalContent(
                "Allgemeine Geschäftsbedingungen",
                listOf(
                    "Durch den Zugriff auf und die Nutzung von Crown Vault stimmen Sie diesen Allgemeinen Geschäftsbedingungen zu. Crown Tech™ stellt die Plattform als Tool zur Verwaltung digitaler Vermögenswerte bereit.",
                    "Die Nutzung der Anwendung setzt voraus, dass Sie volljährig sind und für Ihre privaten Schlüssel, Transaktionen und finanziellen Entscheidungen verantwortlich sind. Crown Tech™ haftet nicht für Verluste durch unsachgemäße Nutzung der Plattform.",
                    "Die Plattform kann jederzeit ohne Vorankündigung aktualisiert, geändert oder eingestellt werden. Fortgesetzte Nutzung stellt die Annahme aller Änderungen dar."
                )
            ),
            privacy = LegalContent(
                "Datenschutzrichtlinie",
                listOf(
                    "Crown Vault priorisiert Ihre Privatsphäre. Wir sammeln keine unnötigen persönlichen Daten und verkaufen Ihre Informationen nicht an Dritte.",
                    "Ihre Wallet-Daten, Transaktionen und Einstellungen werden verschlüsselt und nach Möglichkeit lokal gespeichert. Transaktionen werden über das Crownlink-Netzwerk ohne Vermittler verarbeitet.",
                    "Wir können anonyme Nutzungsmetriken zur Verbesserung der Erfahrung sammeln. Diese Metriken lassen keine persönliche Identifizierung zu."
                )
            ),
            usage = LegalContent(
                "Nutzungsbedingungen",
                listOf(
                    "Crown Vault ist als Krypto-Vermögensverwaltungs-Tool konzipiert. Die Nutzung der Anwendung erfolgt auf eigenes Risiko.",
                    "Die Nutzung der Plattform für illegale Aktivitäten, Geldwäsche oder Handlungen, die gegen die geltenden Gesetze in Ihrer Rechtsordnung verstoßen, ist nicht zulässig.",
                    "Staking-, Swap- und Kartenfunktionen können regionalen Verfügbarkeitseinschränkungen unterliegen. Crown Tech™ behält sich das Recht vor, den Zugriff bei missbräuchlicher Nutzung zu beschränken oder auszusetzen."
                )
            ),
            disclaimer = LegalContent(
                "Haftungsausschluss",
                listOf(
                    "Crown Vault ist eine Demonstrations-App zu Bildungs- und Visualisierungszwecken. Die angezeigten Guthaben, Vermögenswerte und Transaktionen sind simuliert und stellen keine echten Finanzanlagen dar.",
                    "Crown Tech™ gibt keine Finanzberatung. Kryptowährungen und digitale Vermögenswerte bergen erhebliche Risiken, einschließlich Totalverlust. Konsultieren Sie einen professionellen Berater, bevor Sie Anlageentscheidungen treffen.",
                    "Diese Anwendung stellt kein Angebot regulierter Finanzdienstleistungen dar. Alle Marken, Logos und Designs sind Eigentum von Crown Tech™."
                )
            )
        ),
        common = CommonStrings("Verifiziert", "JETZT", ZERO_KNOWLEDGE_BADGE),
        vaultPlus = VaultPlusStrings(
            "Vault+ freischalten", "7-Tage kostenlose Testversion", "Monatlich", "Jährlich", "25% sparen", "/Mo", "/Jr",
            "Sofortige Abwicklung", "Verzögerungsfreie Asset-Transfers im Crownlink-Netzwerk.",
            "2x Staking-Belohnungen", "Erhöhte Rendite auf alle gestakten Assets (CRWN, ETH, BTC).",
            "Priorisiertes Routing", "Zugang zu hochgeschwindigen Liquiditätsrouten mit niedrigeren Gebühren.",
            "Erweiterte Sicherheitsabschirmung", "Mehrschichtige Vault-Verschlüsselung & Echtzeit-Bedrohungserkennung.",
            "Quantum-Virtuellkarte", "Unbegrenzte virtuelle Kartenerstellung ohne FX-Gebühren.",
            "7-Tage kostenlose Testversion starten ($9.99/Mo danach)", "Jederzeit in den Einstellungen kündbar. Gesichert durch Crownlink Protocol.",
            "Vault+ Aktiv", FREEMIUM_MESSAGE, FREE_TIER_NOTE
        ),
        toast = ToastStrings(
            "CRWN generiert jetzt Rendite", "Kurs aktualisiert", "Vault+ Test gestartet",
            "Details der virtuellen Karte kopiert", "Karte vorübergehend gesperrt", "Karte entsperrt",
            "Sicherheits-Scan abgeschlossen — keine Probleme", "Profil erfolgreich exportiert",
            "Willkommen bei Vault+! Testversion erfolgreich gestartet", "Auftrag erfolgreich platziert"
        )
    ),
    Lang.FR to TranslationSet(
        nav = NavStrings("Coffre", "Marchés", "Staking", "Swap", "Carte", "Vault+", "Système"),
        topbar = TopbarStrings("Réseau : Crownlink", "EN DIRECT"),
        vault = VaultStrings(
            "SOLDE TOTAL DU COFFRE", "Envoyer", "Recevoir", "PORTEFEUILLE", "Vos actifs",
            "Gérer", "Générer du rendement", "Jusqu'à 14,2% APY", "Avantages Vault+", "3 nouveaux avantages"
        ),
        markets = MarketsStrings(
            "FLUX D'INTELLIGENCE", "Pouls du marché", "Signaux en temps réel du réseau Crownlink.",
            "MEILLEURE PERFORMANCE", "Neon Index", "Synthétiques globaux · 24H", "ajouté à votre watchlist",
            "Aperçu du marché", "Trading actif", "Acheter", "Vendre", "Quantité", "Type d'ordre",
            "Marché", "Limite", "Placer l'ordre", "Graphique de prix", "Sélectionner un actif"
        ),
        staking = StakingStrings(
            "REVENU PASSIF", "Stakez et croissez", "Mettez vos actifs au travail dans l'économie quantique.",
            "Total staké", "+$18.42 gagnés cette semaine", "Commencez à gagner aujourd'hui", "APY actuel",
            "Période de blocage", "Flexible", "Frais de réseau", "Staking actif", "Staker CRWN",
            "Protégé par Crownlink", "Les actifs sont sécurisés par une validation multicouche et un protocole audité."
        ),
        swap = SwapStrings(
            "ÉCHANGE INSTANTANÉ", "Quantum swap", "Échangez dans le coffre. Sans friction, sans bruit.",
            "VOUS PAYEZ", "VOUS RECEVEZ", "Solde", "Meilleure route", "Vérifier le swap",
            "Propulsé par", "CROWNLINK ROUTER"
        ),
        card = CardStrings(
            "L'AVENIR DES DÉPENSES", "Quantum Card", "Une carte. Chaque dimension.",
            "Carte prête", "Carte virtuelle · se termine par 2389", "Détails", "Bloquer la carte", "Débloquer la carte",
            "DÉPENSES RÉCENTES", "Activité", "Tout voir", "Orbital Market", "Aujourd'hui, 10:42",
            "Récompense cashback", "Hier, 18:20"
        ),
        plus = PlusStrings(
            "LA MISE À NIVEAU", "Vault", "Débloquez tout le spectre de votre richesse.",
            "Essai gratuit", "Règlement instantané", "Déplacez des actifs en moins de 2 secondes",
            "2× récompenses", "Gagnez plus à chaque paiement par carte", "Routage prioritaire",
            "Accédez aux chemins de marché les plus rapides", "Curaté pour la prochaine ère", "Rejoignez 18 402 membres early"
        ),
        system = SystemStrings(
            "PARAMÈTRES PRINCIPAUX", "Système", "Votre coffre, accordé à votre fréquence.",
            "Centre de sécurité", "Tous les systèmes nominaux", "Apparence", "Minuit / Néon",
            "Préférence réseau", "Crownlink · 12 ms", "Notifications", "Alertes intelligentes activées",
            "Scanner la sécurité", "Analyse…", "CROWN VAULT OS", "Langue", "Français",
            "Apparence", "Minuit", "Néon", "Auto", "Préférence réseau",
            "Crownlink · 12 ms", "Quantum Mesh · 28 ms", "Direct Link · 45 ms",
            "Notifications", "Alertes intelligentes", "Alertes de transaction par IA",
            "Alertes de prix", "Notifier les mouvements de prix significatifs",
            "Alertes de sécurité", "Événements de sécurité critiques", "Notifications push activées",
            "Thème appliqué", "Réseau changé avec succès", "Paramètres de notification enregistrés"
        ),
        security = SecurityStrings(
            "CENTRE DE PROTECTION", "Sécurité", "Votre coffre, fortifié à chaque couche.",
            "Verrouillage biométrique", "Face ID · Activé", "Authentification à deux facteurs", "Authentificateur actif",
            "Analyse des transactions", "Surveillance en temps réel", "Adresses de confiance", "3 adresses vérifiées",
            "Scanner la sécurité complète", "Tous les systèmes nominaux",
            "Dernier scan : à l'instant · 0 menace détectée sur toutes les couches.",
            "Analyse…", "Systèmes nominaux", "Zéro menace détectée", "Analyse de toutes les couches de sécurité…"
        ),
        profile = ProfileStrings(
            "VOTRE IDENTITÉ", "Profil", "Gérez votre identité Crown Vault.",
            "Nom d'affichage", "Statut de vérification", "Vérifié · Niveau 3",
            "Adresse du portefeuille", "Exporter les données du profil"
        ),
        drawer = DrawerStrings(
            "Configuration", "Préférences de l'app", "Sécurité", "Centre de protection",
            "Notifications", "Boîte d'alertes", "Profil", "Traveler 07 · Vérifié",
            "SOLDE DU COFFRE", "Légal"
        ),
        modal = ModalStrings(
            "BOÎTE DE RÉCEPTION", "Notifications", "Vous êtes à jour", "La nouvelle activité apparaîtra ici.",
            "TRANSFERT SORTANT", "TRANSFERT ENTRANT", "Envoyer des actifs", "Recevoir des actifs",
            "Collez une adresse de portefeuille", "Continuer", "Copier l'adresse",
            "Transfert préparé pour révision", "Adresse copiée dans le presse-papiers"
        ),
        legal = LegalStrings(
            terms = LegalContent(
                "Termes et Conditions",
                listOf(
                    "En accédant et en utilisant Crown Vault, vous acceptez d'être lié par ces Termes et Conditions. Crown Tech™ fournit la plateforme comme un outil de gestion d'actifs numériques.",
                    "L'utilisation de l'application implique que vous êtes majeur et responsable de vos clés privées, transactions et décisions financières. Crown Tech™ n'est pas responsable des pertes découlant d'une utilisation abusive de la plateforme.",
                    "La plateforme peut être mise à jour, modifiée ou interrompue à tout moment sans préavis. L'utilisation continue constitue l'acceptation de tout changement."
                )
            ),
            privacy = LegalContent(
                "Politique de Confidentialité",
                listOf(
                    "Crown Vault priorise votre confidentialité. Nous ne collectons pas de données personnelles inutiles et ne vendons pas vos informations à des tiers.",
                    "Les données de votre portefeuille, transactions et préférences sont stockées de manière chiffrée et localement lorsque c'est possible. Les transactions sont traitées via le réseau Crownlink sans intermédiaires.",
                    "Nous pouvons collecter des métriques d'utilisation anonymes pour améliorer l'expérience. Ces métriques ne permettent pas l'identification personnelle."
                )
            ),
            usage = LegalContent(
                "Conditions d'Utilisation",
                listOf(
                    "Crown Vault est conçu comme un outil de gestion de crypto-actifs. L'utilisation de l'application est à vos propres risques.",
                    "Il n'est pas permis d'utiliser la plateforme pour des activités illicites, le blanchiment d'argent ou toute action violant les lois applicables dans votre juridiction.",
                    "Les fonctionnalités de staking, swap et carte peuvent être soumises à une disponibilité régionale. Crown Tech™ se réserve le droit de limiter ou suspendre l'accès en cas d'utilisation abusive."
                )
            ),
            disclaimer = LegalContent(
                "Avertissement",
                listOf(
                    "Crown Vault est une application de démonstration à des fins éducatives et de visualisation. Les soldes, actifs et transactions affichés sont simulés et ne représentent pas des actifs financiers réels.",
                    "Crown Tech™ ne fournit pas de conseils financiers. Les cryptomonnaies et actifs numériques comportent des risques importants, y compris la perte totale du capital. Consultez un conseiller professionnel avant de prendre des décisions d'investissement.",
                    "Cette application ne constitue pas une offre de services financiers réglementés. Toutes les marques, logos et designs sont la propriété de Crown Tech™."
                )
            )
        ),
        common = CommonStrings("Vérifié", "MAINTENANT", ZERO_KNOWLEDGE_BADGE),
        vaultPlus = VaultPlusStrings(
            "Débloquer Vault+", "Essai gratuit de 7 jours", "Mensuel", "Annuel", "Économisez 25%", "/mois", "/an",
            "Règlement Instantané", "Transferts d'actifs sans délai sur le réseau Crownlink.",
            "2x Récompenses de Staking", "Rendement boosté sur tous les actifs stakés (CRWN, ETH, BTC).",
            "Routage Prioritaire", "Accès aux routes liquides haute vitesse avec des frais réduits.",
            "Bouclier de Sécurité Avancé", "Chiffrement multicouche du coffre et détection de menaces en temps réel.",
            "Carte Virtuelle Quantique", "Création illimitée de cartes virtuelles sans frais FX.",
            "Commencer l'essai gratuit de 7 jours ($9.99/mois après)", "Annulez à tout moment dans les paramètres. Sécurisé par Crownlink Protocol.",
            "Vault+ Actif", FREEMIUM_MESSAGE, FREE_TIER_NOTE
        ),
        toast = ToastStrings(
            "CRWN génère maintenant du rendement", "Cotation mise à jour", "Essai Vault+ démarré",
            "Détails de la carte virtuelle copiés", "Carte temporairement bloquée", "Carte débloquée",
            "Scan de sécurité terminé — aucun problème", "Profil exporté avec succès",
            "Bienvenue sur Vault+ ! Essai démarré avec succès", "Ordre placé avec succès"
        )
    ),
    Lang.AR to TranslationSet(
        nav = NavStrings("الخزنة", "الأسواق", "التحصين", "التبديل", "البطاقة", "Vault+", "النظام"),
        topbar = TopbarStrings("الشبكة: Crownlink", "مباشر"),
        vault = VaultStrings(
            "إجمالي رصيد الخزنة", "إرسال", "استلام", "المحفظة", "أصولك",
            "إدارة", "اكسب عائداً", "حتى 14.2% APY", "مزايا Vault+", "3 مزايا جديدة"
        ),
        markets = MarketsStrings(
            "تغذية المعلومات", "نبض السوق", "إشارات في الوقت الحقيقي من شبكة Crownlink.",
            "الأعلى ارتفاعاً", "Neon Index", "أصول مركبة عالمية · 24 ساعة", "تمت إضافته إلى قائمة المراقبة",
            "نظرة عامة على السوق", "تداول نشط", "شراء", "بيع", "كمية الطلب", "نوع الطلب",
            "سوق", "حد", "تنفيذ الطلب", "مخطط السعر", "اختر الأصل للتداول"
        ),
        staking = StakingStrings(
            "دخل سلبي", "حصّن ونمِ", "ضع أصولك للعمل في الاقتصاد الكمي.",
            "إجمالي المحصّن", "+$18.42 ربح هذا الأسبوع", "ابدأ الربح اليوم", "APY الحالي",
            "فترة القفل", "مرن", "رسوم الشبكة", "التحصين نشط", "تحصين CRWN",
            "محمي بواسطة Crownlink", "الأصول محمية بواسطة تحقق متعدد الطبقات وبروتوكول مدقق."
        ),
        swap = SwapStrings(
            "تبديل فوري", "تبديل كمي", "بدّل داخل الخزنة. بلا احتكاك، بلا ضوضاء.",
            "تدفع", "تستلم", "الرصيد", "أفضل مسار", "مراجعة التبديل",
            "مدعوم من", "CROWNLINK راوتر"
        ),
        card = CardStrings(
            "مستقبل الإنفاق", "بطاقة كمومية", "بطاقة واحدة. كل بُعد.",
            "البطاقة جاهزة", "بطاقة افتراضية · تنتهي بـ 2389", "التفاصيل", "قفل البطاقة", "فتح البطاقة",
            "الإنفاق الأخير", "النشاط", "عرض الكل", "Orbital Market", "اليوم، 10:42",
            "مكافأة استرداد النقود", "أمس، 18:20"
        ),
        plus = PlusStrings(
            "الترقية", "Vault", "افتح الطيف الكامل لثروتك.",
            "تجربة مجانية", "تسوية فورية", "انقل الأصول في أقل من ثانيتين",
            "مكافآت 2×", "اكسب أكثر عند كل إنفاق بالبطاقة", "توجيه ذو أولوية",
            "الوصول إلى أسرع مسارات السوق", "مختار للعصر القادم", "انضم إلى 18،402 عضواً مبكراً"
        ),
        system = SystemStrings(
            "الإعدادات الأساسية", "النظام", "خزنتك، مضبوطة على ترددك.",
            "مركز الأمان", "جميع الأنظمة طبيعية", "المظهر", "منتصف الليل / نيون",
            "تفضيل الشبكة", "Crownlink · 12 مللي ثانية", "الإشعارات", "تنبيهات ذكية مفعلة",
            "فحص الأمان", "جارٍ الفحص…", "CROWN VAULT OS", "اللغة", "العربية",
            "المظهر", "منتصف الليل", "نيون", "تلقائي", "تفضيل الشبكة",
            "Crownlink · 12 مللي ثانية", "Quantum Mesh · 28 مللي ثانية", "Direct Link · 45 مللي ثانية",
            "الإشعارات", "تنبيهات ذكية", "تنبيهات معاملات بالذكاء الاصطناعي",
            "تنبيهات الأسعار", "إخطار بالحركات السعرية الكبيرة",
            "تنبيهات الأمان", "أحداث أمان حرجة", "الإشعارات الفورية مفعلة",
            "تم تطبيق السمة", "تم تبديل الشبكة بنجاح", "تم حفظ إعدادات الإشعارات"
        ),
        security = SecurityStrings(
            "مركز الحماية", "الأمان", "خزنتك، محصنة في كل طبقة.",
            "قفل بيومتري", "Face ID · مفعّل", "مصادقة ثنائية", "المصادق نشط",
            "فحص المعاملات", "مراقبة في الوقت الحقيقي", "عناوين موثوقة", "3 عناوين موثوقة",
            "فحص أمان كامل", "جميع الأنظمة طبيعية",
            "آخر فحص: الآن · 0 تهديدات مكتشفة في جميع الطبقات.",
            "جارٍ الفحص…", "الأنظمة طبيعية", "صفر تهديدات مكتشفة", "جارٍ فحص جميع طبقات الأمان…"
        ),
        profile = ProfileStrings(
            "هويتك", "الملف الشخصي", "إدارة هوية Crown Vault الخاصة بك.",
            "اسم العرض", "حالة التحقق", "موثّق · المستوى 3",
            "عنوان المحفظة", "تصدير بيانات الملف الشخصي"
        ),
        drawer = DrawerStrings(
            "الإعدادات", "تفضيلات التطبيق", "الأمان", "مركز الحماية",
            "الإشعارات", "صندوق التنبيهات", "الملف الشخصي", "Traveler 07 · موثّق",
            "رصيد الخزنة", "قانوني"
        ),
        modal = ModalStrings(
            "صندوق الوارد", "الإشعارات", "لقد طلعت على كل شيء", "النشاط الجديد سيظهر هنا.",
            "تحويل صادر", "تحويل وارد", "إرسال أصول", "استلام أصول",
            "الصق عنوان المحفظة", "متابعة", "نسخ العنوان",
            "تم تحضير التحويل للمراجعة", "تم نسخ العنوان إلى الحافظة"
        ),
        legal = LegalStrings(
            terms = LegalContent(
                "الشروط والأحكام",
                listOf(
                    "بالدخول إلى Crown Vault واستخدامه، فإنك توافق على الالتزام بهذه الشروط والأحكام. توفر Crown Tech™ المنصة كأداة لإدارة الأصول الرقمية.",
                    "استخدام التطبيق يعني أنك بالغ ومسؤول عن مفاتيحك الخاصة ومعاملاتك وقراراتك المالية. Crown Tech™ غير مسؤولة عن الخسائر الناتجة عن الاستخدام غير السليم للمنصة.",
                    "قد يتم تحديث المنصة أو تعديلها أو إيقافها في أي وقت دون إشعار مسبق. الاستمرار في الاستخدام يشكل قبولاً لأي تغييرات."
                )
            ),
            privacy = LegalContent(
                "سياسة الخصوصية",
                listOf(
                    "يعطي Crown Vault الأولوية لخصوصيتك. لا نجمع بيانات شخصية غير ضرورية ولا نبيع معلوماتك لأطراف ثالثة.",
                    "تُخزن بيانات محفظتك ومعاملاتك وتفضيلاتك مشفرة ومحلياً عندما يكون ذلك ممكناً. تتم معالجة المعاملات عبر شبكة Crownlink دون وسطاء.",
                    "قد نجمع مقاييس استخدام مجهولة لتحسين التجربة. هذه المقاييس لا تسمح بالتعريف الشخصي."
                )
            ),
            usage = LegalContent(
                "شروط الاستخدام",
                listOf(
                    "تم تصميم Crown Vault كأداة لإدارة الأصول المشفرة. استخدام التطبيق على مسؤوليتك الخاصة.",
                    "لا يُسمح باستخدام المنصة لأنشطة غير قانونية أو غسيل أموال أو أي إجراء ينتهك القوانين المعمول بها في ولايتك القضائية.",
                    "قد تخضع ميزات التحصين والتبديل والبطاقة للتوافر الإقليمي. تحتفظ Crown Tech™ بالحق في تقييد الوصول أو تعليقه في حالة الاستخدام غير السليم."
                )
            ),
            disclaimer = LegalContent(
                "إخلاء المسؤولية",
                listOf(
                    "Crown Vault هو تطبيق تجريبي لأغراض تعليمية وتصورية. الأرصدة والأصول والمعاملات المعروضة محاكاة ولا تمثل أصولاً مالية حقيقية.",
                    "لا تقدم Crown Tech™ نصائح مالية. تنطوي العملات المشفرة والأصول الرقمية على مخاطر كبيرة، بما في ذلك خسارة رأس المال بالكامل. استشر مستشاراً مهنياً قبل اتخاذ قرارات الاستثمار.",
                    "لا يشكل هذا التطبيق عرضاً لخدمات مالية منظمة. جميع العلامات التجارية والشعارات والتصاميم ملكية Crown Tech™."
                )
            )
        ),
        common = CommonStrings("موثّق", "الآن", ZERO_KNOWLEDGE_BADGE),
        vaultPlus = VaultPlusStrings(
            "فتح Vault+", "تجربة مجانية لمدة 7 أيام", "شهري", "سنوي", "وفّر 25%", "/شهر", "/سنة",
            "تسوية فورية", "تحويلات أصول بدون تأخير عبر شبكة Crownlink.",
            "2x مكافآت التحصين", "عائد مضاعف على جميع الأصول المحصنة (CRWN، ETH، BTC).",
            "توجيه ذو أولوية", "الوصول إلى مسارات سيولة عالية السرعة برسوم شبكة أقل.",
            "درع أمان متقدم", "تشفير متعدد الطبقات للخزنة وكشف التهديدات في الوقت الحقيقي.",
            "بطاقة افتراضية كمومية", "إنشاء غير محدود للبطاقات الافتراضية بدون رسوم صرف أجنبي.",
            "ابدأ التجربة المجانية لمدة 7 أيام ($9.99/شهر بعد ذلك)", "ألغِ في أي وقت من الإعدادات. محمي بواسطة Crownlink Protocol.",
            "Vault+ نشط", FREEMIUM_MESSAGE, FREE_TIER_NOTE
        ),
        toast = ToastStrings(
            "CRWN يولد عائداً الآن", "تم تحديث السعر", "بدأت تجربة Vault+",
            "تم نسخ تفاصيل البطاقة الافتراضية", "تم قفل البطاقة مؤقتاً", "تم فتح البطاقة",
            "اكتمل فحص الأمان — لا توجد مشاكل", "تم تصدير الملف الشخصي بنجاح",
            "مرحباً بك في Vault+! تم بدء التجربة بنجاح", "تم تنفيذ الطلب بنجاح"
        )
    ),
    Lang.RU to TranslationSet(
        nav = NavStrings("Хранилище", "Рынки", "Стейкинг", "Свап", "Карта", "Vault+", "Система"),
        topbar = TopbarStrings("Сеть: Crownlink", "В ЭФИРЕ"),
        vault = VaultStrings(
            "ОБЩИЙ БАЛАНС ХРАНИЛИЩА", "Отправить", "Получить", "ПОРТФЕЛЬ", "Ваши активы",
            "Управлять", "Зарабатывать", "До 14.2% APY", "Привилегии Vault+", "3 новых преимущества"
        ),
        markets = MarketsStrings(
            "ЛЕНТА ИНТЕЛЛЕКТА", "Пульс рынка", "Сигналы в реальном времени из сети Crownlink.",
            "ТОП ДВИЖЕНИЕ", "Neon Index", "Глобальные синтетики · 24ч", "добавлен в ваш список наблюдения",
            "Обзор рынка", "Активная торговля", "Купить", "Продать", "Объём заказа", "Тип ордера",
            "Рыночный", "Лимитный", "Разместить ордер", "График цены", "Выбрать актив"
        ),
        staking = StakingStrings(
            "ПАССИВНЫЙ ДОХОД", "Стейк и расти", "Заставьте свои активы работать в квантовой экономике.",
            "Всего в стейкинге", "+$18.42 заработано на этой неделе", "Начните зарабатывать сегодня", "Текущий APY",
            "Период блокировки", "Гибкий", "Комиссия сети", "Стейкинг активен", "Стейк CRWN",
            "Защищено Crownlink", "Активы защищены многослойной проверкой и аудированным протоколом."
        ),
        swap = SwapStrings(
            "МГНОВЕННЫЙ ОБМЕН", "Квантовый свап", "Обмен внутри хранилища. Без трения, без шума.",
            "ВЫ ПЛАТИТЕ", "ВЫ ПОЛУЧАЕТЕ", "Баланс", "Лучший маршрут", "Проверить свап",
            "На базе", "CROWNLINK РОУТЕР"
        ),
        card = CardStrings(
            "БУДУЩЕЕ РАСХОДОВ", "Квантовая карта", "Одна карта. Каждое измерение.",
            "Карта готова", "Виртуальная карта · оканчивается на 2389", "Детали", "Заблокировать карту", "Разблокировать карту",
            "НЕДАВНИЕ РАСХОДЫ", "Активность", "Все", "Orbital Market", "Сегодня, 10:42",
            "Кэшбэк-вознаграждение", "Вчера, 18:20"
        ),
        plus = PlusStrings(
            "ОБНОВЛЕНИЕ", "Vault", "Откройте полный спектр вашего богатства.",
            "Бесплатный пробный период", "Мгновенное расчет", "Перемещайте активы менее чем за 2 секунды",
            "2× награды", "Зарабатывайте больше при каждой трате по карте", "Приоритетная маршрутизация",
            "Доступ к самым быстрым рыночным путям", "Отобрано для следующей эры", "Присоединяйтесь к 18 402 ранним участникам"
        ),
        system = SystemStrings(
            "ОСНОВНЫЕ НАСТРОЙКИ", "Система", "Ваше хранилище, настроенное на вашу частоту.",
            "Центр безопасности", "Все системы в норме", "Внешний вид", "Полночь / Неон",
            "Настройки сети", "Crownlink · 12 мс", "Уведомления", "Умные оповещения включены",
            "Сканировать безопасность", "Сканирование…", "CROWN VAULT OS", "Язык", "Русский",
            "Внешний вид", "Полночь", "Неон", "Авто", "Настройки сети",
            "Crownlink · 12 мс", "Quantum Mesh · 28 мс", "Direct Link · 45 мс",
            "Уведомления", "Умные оповещения", "Оповещения о транзакциях на базе ИИ",
            "Оповещения о цене", "Уведомлять о значительных изменениях цены",
            "Оповещения безопасности", "Критические события безопасности", "Push-уведомления включены",
            "Тема применена", "Сеть переключена успешно", "Настройки уведомлений сохранены"
        ),
        security = SecurityStrings(
            "ЦЕНТР ЗАЩИТЫ", "Безопасность", "Ваше хранилище, укреплённое на каждом уровне.",
            "Биометрическая блокировка", "Face ID · Включено", "Двухфакторная аутентификация", "Аутентификатор активен",
            "Сканирование транзакций", "Мониторинг в реальном времени", "Доверенные адреса", "3 доверенных адреса",
            "Полное сканирование безопасности", "Все системы в норме",
            "Последнее сканирование: только что · 0 угроз обнаружено на всех уровнях.",
            "Сканирование…", "Системы в норме", "Ноль угроз обнаружено", "Сканирование всех уровней безопасности…"
        ),
        profile = ProfileStrings(
            "ВАША ИДЕНТИЧНОСТЬ", "Профиль", "Управляйте вашей идентичностью Crown Vault.",
            "Отображаемое имя", "Статус верификации", "Верифицирован · Уровень 3",
            "Адрес кошелька", "Экспорт данных профиля"
        ),
        drawer = DrawerStrings(
            "Настройки", "Настройки приложения", "Безопасность", "Центр защиты",
            "Уведомления", "Папка оповещений", "Профиль", "Traveler 07 · Верифицирован",
            "БАЛАНС ХРАНИЛИЩА", "Правовое"
        ),
        modal = ModalStrings(
            "ВХОДЯЩИЕ", "Уведомления", "Вы всё просмотрели", "Новая активность появится здесь.",
            "ИСХОДЯЩИЙ ПЕРЕВОД", "ВХОДЯЩИЙ ПЕРЕВОД", "Отправить активы", "Получить активы",
            "Вставьте адрес кошелька", "Продолжить", "Копировать адрес",
            "Перевод подготовлен к проверке", "Адрес скопирован в буфер обмена"
        ),
        legal = LegalStrings(
            terms = LegalContent(
                "Условия и положения",
                listOf(
                    "Получая доступ к Crown Vault и используя его, вы соглашаетесь соблюдать эти Условия и положения. Crown Tech™ предоставляет платформу как инструмент управления цифровыми активами.",
                    "Использование приложения означает, что вы совершеннолетний и несёте ответственность за свои приватные ключи, транзакции и финансовые решения. Crown Tech™ не несёт ответственности за убытки, возникшие в результате ненадлежащего использования платформы.",
                    "Платформа может обновляться, изменяться или прекращаться в любое время без предварительного уведомления. Продолжение использования означает согласие с любыми изменениями."
                )
            ),
            privacy = LegalContent(
                "Политика конфиденциальности",
                listOf(
                    "Crown Vault ставит вашу конфиденциальность на первое место. Мы не собираем ненужные личные данные и не продаём вашу информацию третьим лицам.",
                    "Данные вашего кошелька, транзакции и настройки хранятся в зашифрованном виде и локально, когда это возможно. Транзакции обрабатываются через сеть Crownlink без посредников.",
                    "Мы можем собирать анонимные метрики использования для улучшения опыта. Эти метрики не позволяют идентифицировать личность."
                )
            ),
            usage = LegalContent(
                "Условия использования",
                listOf(
                    "Crown Vault разработан как инструмент управления криптоактивами. Использование приложения осуществляется на ваш собственный риск.",
                    "Запрещается использовать платформу для незаконной деятельности, отмывания денег или любых действий, нарушающих законы вашей юрисдикции.",
                    "Функции стейкинга, свапа и карты могут зависеть от региональной доступности. Crown Tech™ оставляет за собой право ограничить или приостановить доступ в случае ненадлежащего использования."
                )
            ),
            disclaimer = LegalContent(
                "Отказ от ответственности",
                listOf(
                    "Crown Vault — демонстрационное приложение в образовательных и визуализационных целях. Отображаемые балансы, активы и транзакции смоделированы и не представляют реальные финансовые активы.",
                    "Crown Tech™ не предоставляет финансовых консультаций. Криптовалюты и цифровые активы несут значительные риски, включая полную потерю капитала. Проконсультируйтесь с профессиональным консультантом перед принятием инвестиционных решений.",
                    "Это приложение не является предложением регулируемых финансовых услуг. Все товарные знаки, логотипы и дизайны являются собственностью Crown Tech™."
                )
            )
        ),
        common = CommonStrings("Верифицирован", "СЕЙЧАС", ZERO_KNOWLEDGE_BADGE),
        vaultPlus = VaultPlusStrings(
            "Разблокировать Vault+", "7-дневный бесплатный пробный период", "Ежемесячно", "Ежегодно", "Сэкономьте 25%", "/мес", "/год",
            "Мгновенные расчёты", "Переводы активов без задержки в сети Crownlink.",
            "2x награды за стейкинг", "Повышенная доходность по всем стейкинг-активам (CRWN, ETH, BTC).",
            "Приоритетная маршрутизация", "Доступ к высокоскоростным ликвидным маршрутам с меньшими комиссиями.",
            "Расширенный щит безопасности", "Многослойное шифрование хранилища и обнаружение угроз в реальном времени.",
            "Квантовая виртуальная карта", "Неограниченное создание виртуальных карт без комиссий FX.",
            "Начать 7-дневный бесплатный пробный период ($9.99/мес после)", "Отмена в любое время в настройках. Защищено Crownlink Protocol.",
            "Vault+ Активен", FREEMIUM_MESSAGE, FREE_TIER_NOTE
        ),
        toast = ToastStrings(
            "CRWN теперь генерирует доход", "Курс обновлён", "Пробный период Vault+ начался",
            "Детали виртуальной карты скопированы", "Карта временно заблокирована", "Карта разблокирована",
            "Сканирование безопасности завершено — проблем не найдено", "Профиль успешно экспортирован",
            "Добро пожаловать в Vault+! Пробный период успешно начат", "Ордер успешно размещён"
        )
    )
)
