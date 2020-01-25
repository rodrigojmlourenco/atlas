object LibraryDeps : DepsCollection {

    override val all: List<Config> = listOf(
        Config.Impl(JetpackDeps.ktx),
        Config.Impl(SupportDeps.timber)
    )
        .plus(UnitTestDeps.all)
        .plus(InstrumentedTestDeps.all)
}