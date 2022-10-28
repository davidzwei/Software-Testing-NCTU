# Lab8
* 我們提供一個 linux binary，請透過 angr 產生出他的 cfg
* 請找到能夠讓該程式印出 correct\n 的輸入
* 繳交方式 : 學號.zip (zip內包含以下檔案)
    * target.png : 用來找位置的 cfg 圖片
    * solve.py : 執行 angr 的 python (please use python3)
    * flag.txt : 正確的參數輸入 (argv[1])
* Note :
    * 此 Lab 有開 pie
        * angr 會自動把 pie base 放到 0x400000 
        * 照著投影片第16頁步驟所產出來的cfg內地址已經是rebase過的
