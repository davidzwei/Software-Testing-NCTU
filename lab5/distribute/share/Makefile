CC := clang-11
CXX := clang++-11
CFLAGS := `llvm-config-11 --ldflags` -g -ggdb
CXXFLAGS := -fno-rtti -fPIC `llvm-config-11 --cxxflags` `llvm-config-11 --ldflags` -shared

.PHONY: all clean
all:
	$(CXX) $(CXXFLAGS) ./llvm-lab-pass.cc -o llvm-lab-pass.so

	$(CC) $(CFLAGS) -Xclang -load -Xclang ./llvm-lab-pass.so target.c -o target

clean:
	rm -f ./llvm-lab-pass.so
	rm -f target