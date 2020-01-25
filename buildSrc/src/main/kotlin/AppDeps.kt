object AppDeps : DepsCollection {

    override val all = emptyList<Config>()
        .asSequence()
        .plus(JetpackDeps.all)
        .plus(PlayServicesDeps.all)
        .plus(RoomDeps.all)
        .plus(RxDeps.all)
        .plus(DaggerDeps.all)
        .plus(NetworkDeps.all)
        .plus(UxDeps.all)
        .plus(SupportDeps.all)
        .plus(UnitTestDeps.all)
        .plus(InstrumentedTestDeps.all)
        .toList()
}