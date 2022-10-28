# Lab6

* 下面是常見的記憶體操作問題，請分別寫出有下列記憶體操作問題的簡單程式，並說明 Valgrind 和 ASan 能否找的出來
    * Heap out-of-bounds read/write
    * Stack out-of-bounds read/write
    * Global out-of-bounds read/write
    * Use-after-free
    * Use-after-return
* 寫一個簡單程式 with ASan，Stack buffer overflow 剛好越過 redzone(並沒有對 redzone 做讀寫)，並說明 ASan 能否找的出來？
