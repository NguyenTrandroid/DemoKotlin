package nguyentrandroid.a.hhll.data.repositories



//class NotifyRepositories private constructor() {
//
//    companion object {
//        var notifyService = API.getClient().create(NotifyService::class.java)
//        val NETWORK_IO = Executors.newFixedThreadPool(5)
//        var factoty = NotifyDataSourceFactory(null, notifyService, NETWORK_IO)
//        val INSTANCE = NotifyRepositories
//
////        suspend fun insert(dao: NotifyDao, itemNotifyDB: ItemNotifyDB) {
////            dao.insert(itemNotifyDB)
////        }
////
////        fun getAllNotify(dao: NotifyDao): LiveData<List<ItemNotifyDB>> {
////            return dao.getAllNoti()
////        }
////
////        suspend fun getNotify(u: String, l: Int): NotifyResponse {
////            return API.getClient().create(NotifyService::class.java).getNotify(u, l).await()
////        }
//
//        fun getDataListing(scope: CoroutineScope): Listing<Hit> {
//            factoty.scope = scope
//            var pagedListConfig =  PagedList.Config.Builder()  .setEnablePlaceholders(false)
//                .setInitialLoadSizeHint(10)
//                .setPageSize(20)
//            val livePagedList = LivePagedListBuilder<String, Hit>(factoty, pagedListConfig.build())
//                    .setFetchExecutor(Executors.newFixedThreadPool(5))
//                    .build()
//            val refreshState = factoty.sourceLiveData.switchMap {
//                it.initialLoad
//            }
//            return Listing(
//                pagedList = livePagedList,
//                networkState = factoty.sourceLiveData.switchMap {
//                    it.networkState
//                },
//                retry = {
//                    factoty.sourceLiveData.value?.retryAllFailed()
//                },
//                refresh = {
//                    factoty.sourceLiveData.value?.invalidate()
//                },
//                refreshState = refreshState
//            )
//        }
//    }
//}