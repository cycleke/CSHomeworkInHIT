#include <iostream>
#include <stdlib.h>
//#include <stdio.h>
#include <cstring>
#include <memory>
#include <exceptions/bad_buffer_exception.h>
#include <exceptions/invalid_record_exception.h>
#include "page.h"
#include "buffer.h"
#include "file_iterator.h"
#include "page_iterator.h"
#include "exceptions/file_not_found_exception.h"
#include "exceptions/invalid_page_exception.h"
#include "exceptions/page_not_pinned_exception.h"
#include "exceptions/page_pinned_exception.h"
#include "exceptions/buffer_exceeded_exception.h"

#define PRINT_ERROR(str) \
{ \
    std::cerr << "On Line No:" << __LINE__ << "\n"; \
    std::cerr << str << "\n"; \
    exit(1); \
}

using namespace badgerdb;

const PageId num = 100;
PageId pid[num], pageno1, pageno2, pageno3, i, j;
RecordId rid[num], rid2, rid3;
Page *page, *page2, *page3;
char tmpbuf[100], tmpbuf1[100];
BufMgr *bufMgr;
File *file1ptr, *file2ptr, *file3ptr, *file4ptr, *file5ptr, *file6ptr, *file7ptr, *file8ptr, *file9ptr, *file10ptr;

void test1();

void test2();

void test3();

void test4();

void test5();

void test6();

void test7();

void test8();

void test9();

void test10();

void test11();

void test12();

void testBufMgr();

int main() {
    //Following code shows how to you File and Page classes

    const std::string &filename = "test.db";
    // Clean up from any previous runs that crashed.
    try {
        File::remove(filename);
    }
    catch (FileNotFoundException &e) {}
    {
        // Create a new database file.
        File new_file = File::create(filename);

        // Allocate some pages and put data on them.
        PageId third_page_number;
        for (int i = 0; i < 5; ++i) {
            Page new_page = new_file.allocatePage();
            if (i == 3) {
                // Keep track of the identifier for the third page so we can read it
                // later.
                third_page_number = new_page.page_number();
            }
            new_page.insertRecord("hello!");
            // Write the page back to the file (with the new data).
            new_file.writePage(new_page);
        }

        // Iterate through all pages in the file.
        for (FileIterator iter = new_file.begin();
             iter != new_file.end();
             ++iter) {
            // Iterate through all records on the page.
            Page p = *iter;
            for (PageIterator page_iter = p.begin();
                 page_iter != p.end();
                 ++page_iter) {
                std::cout << "Found record: " << *page_iter
                          << " on page " << p.page_number() << "\n";
            }
        }

        // Retrieve the third page and add another record to it.
        Page third_page = new_file.readPage(third_page_number);
        const RecordId &rid = third_page.insertRecord("world!");
        new_file.writePage(third_page);

        // Retrieve the record we just added to the third page.
        std::cout << "Third page has a new record: "
                  << third_page.getRecord(rid) << "\n\n";
    }
    // new_file goes out of scope here, so file is automatically closed.

    // Delete the file since we're done with it.
    File::remove(filename);

    //This function tests buffer manager, comment this line if you don't wish to test buffer manager
    testBufMgr();
}

void testBufMgr() {
    // create buffer manager
    bufMgr = new BufMgr(num);

    // create dummy files
    const std::string &filename1 = "test.1";
    const std::string &filename2 = "test.2";
    const std::string &filename3 = "test.3";
    const std::string &filename4 = "test.4";
    const std::string &filename5 = "test.5";
    const std::string &filename6 = "test.6";
    const std::string &filename7 = "test.7";
    const std::string &filename8 = "test.8";
    const std::string &filename9 = "test.9";
    const std::string &filename10 = "test.10";


    try {
        File::remove(filename1);
        File::remove(filename2);
        File::remove(filename3);
        File::remove(filename4);
        File::remove(filename5);
        File::remove(filename6);
        File::remove(filename7);
        File::remove(filename8);
        File::remove(filename9);
        File::remove(filename10);
    }
    catch (FileNotFoundException & e) {
    }

    File file1 = File::create(filename1);
    File file2 = File::create(filename2);
    File file3 = File::create(filename3);
    File file4 = File::create(filename4);
    File file5 = File::create(filename5);
    File file6 = File::create(filename6);
    File file7 = File::create(filename7);
    File file8 = File::create(filename8);
    File file9 = File::create(filename9);
    File file10 = File::create(filename10);

    file1ptr = &file1;
    file2ptr = &file2;
    file3ptr = &file3;
    file4ptr = &file4;
    file5ptr = &file5;
    file6ptr = &file6;
    file7ptr = &file7;
    file8ptr = &file8;
    file9ptr = &file9;
    file10ptr = &file10;

    //Test buffer manager
    //Comment tests which you do not wish to run now. Tests are dependent on their preceding tests. So, they have to be run in the following order.
    //Commenting  a particular test requires commenting all tests that follow it else those tests would fail.
    test1();
    test2();
    test3();
    test4();
    test5();
    test6();
    test7();
    test8();
    test9();
    test10();
    test11();
    test12();



    //Close files before deleting them
    file1.~File();
    file2.~File();
    file3.~File();
    file4.~File();
    file5.~File();
    file6.~File();
    file7.~File();
    file8.~File();
    file9.~File();
    file10.~File();


    //Delete files
    File::remove(filename1);
    File::remove(filename2);
    File::remove(filename3);
    File::remove(filename4);
    File::remove(filename5);
    File::remove(filename6);
    File::remove(filename7);
    File::remove(filename8);
    File::remove(filename9);
    File::remove(filename10);

    delete bufMgr;

    std::cout << "\n" << "Passed all tests." << "\n";
}

void test1() {
    std::cout << "[Test 1]" << "\n";
    std::cout << "Testing allocatePage() and testing readPage() when pages are in buffer." << "\n";
    //Allocating pages in a file...
    for (i = 0; i < num; i++) {
        bufMgr->allocPage(file1ptr, pid[i], page);
        sprintf((char *) tmpbuf, "test.1 Page %d %7.1f", pid[i], (float) pid[i]);
        rid[i] = page->insertRecord(tmpbuf);
        bufMgr->unPinPage(file1ptr, pid[i], true);
    }

    //Reading pages back...
    for (i = 0; i < num; i++) {
        bufMgr->readPage(file1ptr, pid[i], page);
        sprintf((char *) &tmpbuf, "test.1 Page %d %7.1f", pid[i], (float) pid[i]);
        if (strncmp(page->getRecord(rid[i]).c_str(), tmpbuf, strlen(tmpbuf)) != 0) {
            PRINT_ERROR("ERROR :: CONTENTS DID NOT MATCH");
        }
        bufMgr->unPinPage(file1ptr, pid[i], false);
    }
    std::cout << "Passed" << "\n";
}


void test2() {
    //Writing and reading back multiple files
    //The page number and the value should match
    std::cout << "[Test 2]" << "\n";
    std::cout << "(1) Testing allocatePage() when the buffer pool is full." << "\n" ;
    std::cout << "(2) Testing readPage() when pages are not in buffer." << "\n";
    for (i = 0; i < num / 3; i++) {
        bufMgr->allocPage(file2ptr, pageno2, page2);
        sprintf((char *) tmpbuf, "test.2 Page %d %7.1f", pageno2, (float) pageno2);
        rid2 = page2->insertRecord(tmpbuf);

        int index = rand() % num;
        pageno1 = pid[index];
        bufMgr->readPage(file1ptr, pageno1, page);
        sprintf((char *) tmpbuf, "test.1 Page %d %7.1f", pageno1, (float) pageno1);
        if (strncmp(page->getRecord(rid[index]).c_str(), tmpbuf, strlen(tmpbuf)) != 0) {
            PRINT_ERROR("ERROR :: CONTENTS DID NOT MATCH");
        }

        bufMgr->allocPage(file3ptr, pageno3, page3);
        sprintf((char *) tmpbuf, "test.3 Page %d %7.1f", pageno3, (float) pageno3);
        rid3 = page3->insertRecord(tmpbuf);

        bufMgr->readPage(file2ptr, pageno2, page2);
        sprintf((char *) &tmpbuf, "test.2 Page %d %7.1f", pageno2, (float) pageno2);
        if (strncmp(page2->getRecord(rid2).c_str(), tmpbuf, strlen(tmpbuf)) != 0) {
            PRINT_ERROR("ERROR :: CONTENTS DID NOT MATCH");
        }

        bufMgr->readPage(file3ptr, pageno3, page3);
        sprintf((char *) &tmpbuf, "test.3 Page %d %7.1f", pageno3, (float) pageno3);
        if (strncmp(page3->getRecord(rid3).c_str(), tmpbuf, strlen(tmpbuf)) != 0) {
            PRINT_ERROR("ERROR :: CONTENTS DID NOT MATCH");
        }

        bufMgr->unPinPage(file1ptr, pageno1, false);
    }

    for (i = 0; i < num / 3; i++) {
        bufMgr->unPinPage(file2ptr, i + 1, true);
        bufMgr->unPinPage(file2ptr, i + 1, true);
        bufMgr->unPinPage(file3ptr, i + 1, true);
        bufMgr->unPinPage(file3ptr, i + 1, true);
    }

    std::cout << "Passed" << "\n";
}


void test3() {
    std::cout << "[Test 3]" << "\n";
    std::cout << "Testing readPage() when reading a invalid page." << "\n";
    try {
        bufMgr->readPage(file4ptr, 1, page);
        PRINT_ERROR(
                "ERROR :: File4 should not exist. Exception should have been thrown before execution reaches this point.");
    }
    catch (InvalidPageException & e) {
    }

    std::cout << "Passed" << "\n";
}

void test4() {
    std::cout << "[Test 4]" << "\n";
    std::cout << "Testing unPinPage() when unpining an unpinned page." << "\n";
    bufMgr->allocPage(file4ptr, i, page);
    bufMgr->unPinPage(file4ptr, i, true);
    try {
        bufMgr->unPinPage(file4ptr, i, false);
        PRINT_ERROR(
                "ERROR :: Page is already unpinned. Exception should have been thrown before execution reaches this point.");
    }
    catch (PageNotPinnedException & e) {
    }

    std::cout << "Passed" << "\n";
}


void test5() {
    std::cout << "[Test 5]" << "\n";
    std::cout << "Testing allocatePage() when the buffer pool is full." << "\n";
    for (i = 0; i < num; i++) {
        bufMgr->allocPage(file5ptr, pid[i], page);
        sprintf((char *) tmpbuf, "test.5 Page %d %7.1f", pid[i], (float) pid[i]);
        rid[i] = page->insertRecord(tmpbuf);
    }

    PageId tmp;
    try {
        bufMgr->allocPage(file5ptr, tmp, page);
        PRINT_ERROR(
                "ERROR :: No more frames left for allocation. Exception should have been thrown before execution reaches this point.");
    }
    catch (BufferExceededException & e) {
    }

    std::cout << "Passed" << "\n";

    for (i = 1; i <= num; i++)
        bufMgr->unPinPage(file5ptr, i, true);
}

//
void test6() {
    std::cout << "[Test 6]" << "\n";
    std::cout << "Testing flushFile() when pages are pinned." << "\n";
    //flushing file with pages still pinned. Should generate an error
    for (i = 1; i <= num; i++) {
        bufMgr->readPage(file1ptr, i, page);
    }

    try {
        bufMgr->flushFile(file1ptr);
        PRINT_ERROR(
                "ERROR :: Pages pinned for file being flushed. Exception should have been thrown before execution reaches this point.");
    }
    catch (PagePinnedException & e) {
    }

    std::cout << "Passed" << "\n";

    for (i = 1; i <= num; i++)
        bufMgr->unPinPage(file1ptr, i, true);

    bufMgr->flushFile(file1ptr);
}

void test7(){
    std::cout << "[Test 7]" << "\n";
    std::cout << "Testing disposePage()." << "\n";
    bufMgr->allocPage(file6ptr, i, page);
    sprintf((char*)tmpbuf, "test.7 Page %d %7.1f", i, (float)i);
    page->insertRecord(tmpbuf);
    bufMgr->disposePage(file6ptr, i);
    try
    {
        bufMgr->disposePage(file6ptr, i);
        PRINT_ERROR("ERROR :: The page is deleted. Exception should have been thrown before execution reaches this point.");
    }
    catch (InvalidPageException & e) {}

    std::cout << "Passed" << "\n";
}

void test8(){
    std::cout << "[Test 8]" << "\n";
    std::cout << "Testing if contents match after calling flushFile()." << "\n";
    bufMgr->allocPage(file7ptr, i, page);
    sprintf((char*)tmpbuf, "test.7 Page %d %7.1f", i, (float)i);
    rid2 = page->insertRecord(tmpbuf);
    bufMgr->unPinPage(file7ptr, i, true);
    bufMgr->flushFile(file7ptr);

    bufMgr->readPage(file7ptr,i,page);
    if (strncmp(page->getRecord(rid2).c_str(), tmpbuf, strlen(tmpbuf)) != 0) {
        PRINT_ERROR("ERROR :: CONTENTS DID NOT MATCH");
    }

    sprintf((char*)tmpbuf1, "test.x Page %d %7.1f", i, (float)i);
    page->updateRecord(rid2, tmpbuf1);
    bufMgr->unPinPage(file7ptr, i, false);
    bufMgr->flushFile(file7ptr);

    bufMgr->readPage(file7ptr,i,page);
    if (strncmp(page->getRecord(rid2).c_str(), tmpbuf, strlen(tmpbuf)) != 0) {
        PRINT_ERROR("ERROR :: CONTENTS DID NOT MATCH");
    }

    bufMgr->unPinPage(file7ptr,i, false);
    std::cout << "Passed" << "\n";

}
void test9(){
    std::cout << "[Test 9]" << "\n";
    std::cout << "Testing disposePage() and testing readPage() when reading a deleted page." << "\n";
    bufMgr->disposePage(file7ptr, 1);
    try
    {
        bufMgr->readPage(file7ptr, 1,page);
        PRINT_ERROR("ERROR :: test.7 page:i is not in the hash table. Exception should have been thrown before execution reaches this point.");
    }
    catch (InvalidPageException & e) {}
    std::cout << "Passed" << "\n";
}
void test10(){
    std::cout << "[Test 10]" << "\n";
    std::cout << "Testing the clock algorithm and buffer space management" << "\n";
    bufMgr->allocPage(file8ptr, i, page);
    sprintf((char*)tmpbuf, "test.10 Page %d %7.1f", i, (float)i);
    rid2 = page->insertRecord(tmpbuf);

    try{
        for (j = 0; j < num; j++) {
            bufMgr->allocPage(file8ptr, pid[j], page);
        }
        PRINT_ERROR(
                "ERROR :: No more frames left for allocation. Exception should have been thrown before execution reaches this point.");
    }
    catch (BufferExceededException &e){
        if (j!=num-1){
            PRINT_ERROR(
                    "ERROR :: There are some frames left for allocation. Exception should have been thrown before execution reaches this point.");
        }
    }

    bufMgr->unPinPage(file8ptr,i,true);
    bufMgr->allocPage(file8ptr,pid[num-1],page);
    for (j=0; j < num; j++){
        bufMgr->unPinPage(file8ptr, pid[j], false);
    }

    bufMgr->readPage(file8ptr, i, page);
    if (strncmp(page->getRecord(rid2).c_str(), tmpbuf, strlen(tmpbuf)) != 0) {
        PRINT_ERROR("ERROR :: CONTENTS DID NOT MATCH");
    }
    bufMgr->unPinPage(file8ptr, i, false);
    bufMgr->flushFile(file8ptr);
    std::cout << "Passed" << "\n";
}

void test11(){
    std::cout << "[Test 11]" << "\n";
    std::cout << "Testing readPage() when pages in buffer pool has been updated." << "\n";
    bufMgr->allocPage(file9ptr, i, page);
    sprintf((char*)tmpbuf, "test.11 Page %d %7.1f", i, (float)i);
    rid2 = page->insertRecord(tmpbuf);
    sprintf((char*)tmpbuf1, "test.x Page %d %7.1f", i , (float)i);
    page->updateRecord(rid2, tmpbuf1);
    bufMgr->readPage(file9ptr, i, page);
    if (strncmp(page->getRecord(rid2).c_str(), tmpbuf1, strlen(tmpbuf1)) != 0)
    {
        PRINT_ERROR("ERROR :: CONTENTS DID NOT MATCH");
    }
    bufMgr->unPinPage(file9ptr, i, true);
    try {
        bufMgr->flushFile(file9ptr);
        PRINT_ERROR(
                "ERROR :: Pages pinned for file being flushed. Exception should have been thrown before execution reaches this point.");
    }
    catch (PagePinnedException& e){}
    bufMgr->unPinPage(file9ptr,i, false);
    bufMgr->flushFile(file9ptr);
    std::cout << "Passed" << "\n";
}
void test12(){
    std::cout << "[Test 12]" << "\n";
    std::cout << "Testing the readingPage() when reading a page whose pid is the same with the pid of a deleted page." << "\n";
    bufMgr->allocPage(file10ptr, i, page);
    sprintf((char*)tmpbuf, "test.12 Page %d %7.1f", i, (float)i);
    rid2 = page->insertRecord(tmpbuf);
    bufMgr->unPinPage(file10ptr, i, true);
    bufMgr->disposePage(file10ptr,i);
    bufMgr->allocPage(file10ptr,j,page);

    bufMgr->readPage(file10ptr,i,page);
    try
    {
        page->getRecord(rid2);
        PRINT_ERROR("ERROR :: The record has been deleted. Exception should have been thrown before execution reaches this point.");
    }
    catch (InvalidRecordException & e) {}
    bufMgr->unPinPage(file10ptr,i, false);
    bufMgr->unPinPage(file10ptr,i, false);
    std::cout << "Passed" << "\n";
}