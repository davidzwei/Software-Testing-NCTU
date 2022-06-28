import requests
from selenium import webdriver
from webdriver_manager.chrome import ChromeDriverManager
from selenium.webdriver.chrome.service import Service
from selenium.webdriver.common.keys import Keys 
from selenium.webdriver.common.by import By
import time

# part a
driver = webdriver.Chrome(executable_path=ChromeDriverManager().install())
driver.get("https://www.nycu.edu.tw/")
driver.maximize_window()
# news
driver.find_element_by_xpath('/html/body/div[1]/div/main/div/div/div/article/div/div/div/div/section[2]/div/div/div[1]/div/div/div/div/nav[1]/ul/li[2]/a').click()
time.sleep(1)
# first news
driver.find_element_by_xpath('/html/body/div[1]/div/main/div[1]/div/div/article/div/div/div/div/section/div/div/div/div/div/div[3]/div/div/div[2]/div[1]/ul/li[1]/a').click()
time.sleep(1)
# title
text = driver.find_element_by_xpath('/html/body/div[1]/div/main/div/div/div/article/header/h1').text
print(text)
print("\n")
# content
content = driver.find_element_by_xpath('/html/body/div[1]/div/main/div/div/div/article/div').text
print(content)
time.sleep(1)

# part b----------------------------------------------------------------
print("\n")
# opens in a new browser tab
driver.execute_script("window.open('about:blank', 'secondtab');")
# It is switching to second tab now
driver.switch_to.window("secondtab")
# open google
driver.get('https://www.google.com/')
time.sleep(2)
search = driver.find_element_by_name("q")
driver.implicitly_wait(5)
# # search.clear()
search.send_keys("310552060")
search.send_keys(Keys.RETURN)
time.sleep(2)

# full xpath
# second_search = driver.find_element_by_xpath('/html/body/div[7]/div/div[10]/div/div[2]/div[2]/div/div/div[2]/div/div[1]/div/div/div[1]/div/a/h3').text
# xpath
second_search = driver.find_element_by_xpath('//*[@id="rso"]/div[1]/div/div[1]/div/div/div[1]/div/a/h3').text

print(second_search)
# close two tabs
driver.quit()