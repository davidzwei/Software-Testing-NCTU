FROM ubuntu:20.04
MAINTAINER Tim

RUN apt-get update && \
    DEBIAN_FRONTEND=noninteractive apt-get install -qy xinetd && \
    apt-get install make && \
    apt-get install -y lld-11 llvm-11 llvm-11-dev clang-11
RUN useradd -m llvm-lab
RUN chown -R root:root /home/llvm-lab
RUN chmod -R 755 /home/llvm-lab

CMD ["/usr/sbin/xinetd","-dontfork"]
