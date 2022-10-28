
#include "llvm/Analysis/CFGPrinter.h"
#include "llvm/IR/IRBuilder.h"
#include "llvm/IR/Function.h"
#include "llvm/IR/LegacyPassManager.h"
#include "llvm/IR/Module.h"
#include "llvm/Support/FileSystem.h"
#include "llvm/Support/raw_ostream.h"
#include "llvm/Transforms/IPO/PassManagerBuilder.h"
#include "llvm/Pass.h"
#include "llvm/IR/Module.h"
#include "llvm/IR/Constants.h"
#include "llvm/IR/Type.h"
#include "llvm/IR/IRBuilder.h"


using namespace llvm;

namespace { 

class ExamplePass : public ModulePass {

 public:
  static char ID;
  ExamplePass() : ModulePass(ID) { }
  
  bool doInitialization(Module &M) override;
  bool runOnModule(Module &M) override;

};

}  // namespace

char ExamplePass::ID = 0;

bool ExamplePass::doInitialization(Module &M) {

  return true;

}

bool ExamplePass::runOnModule(Module &M) {
  
  errs() << "runOnModule\n";

  
  for (auto &F : M) {    
    if (F.getName() == "main"){
      // 1. call debug()
      BasicBlock &entryBlock = F.getEntryBlock();
      
      IntegerType *Int32Ty = IntegerType::getInt32Ty(M.getContext());
      Type *VoidTy = Type::getVoidTy(M.getContext());

      std::vector<Type *>FnArgs;
      FnArgs.push_back(Int32Ty);
      FunctionType *FnTy = FunctionType::get(VoidTy, FnArgs, false);
      FunctionCallee Fn = M.getOrInsertFunction("debug", FnTy);
      
      // 1. Invoke debug function with the first argument is 9527 in main function. 
      // insert IR
      BasicBlock::iterator IP = entryBlock.getFirstInsertionPt();
      IRBuilder<> IRB(&(*IP));
      IRB.CreateCall(Fn, ConstantInt::get(Int32Ty, 9527));
      

      // 2. Let argv[1] = "aesophor is ghost !!!" before checking. 
      // make a global string
      Value *Arg_str = IRB.CreateGlobalStringPtr("aesophor is ghost !!!");
      Argument* argv1 = F.getArg(1);
      Value *argv1_ptr = IRB.CreateGEP(argv1, ConstantInt::get(Int32Ty, 1));
      IRB.CreateStore(Arg_str, argv1_ptr);


      // 3. Let argc = 9487 before checking.
      ConstantInt *newArgc = ConstantInt::get(Int32Ty, 9487);
      Argument* argc = F.getArg(0);
      argc->replaceAllUsesWith(newArgc);

    }
    /* add you code here */
    errs() << F.getName() << "\n";
    
  }

  return true;
  
}

static void registerExamplePass(const PassManagerBuilder &,
                                           legacy::PassManagerBase &PM) {

  PM.add(new ExamplePass());

}

static RegisterStandardPasses RegisterExamplePass(
    PassManagerBuilder::EP_OptimizerLast, registerExamplePass);

static RegisterStandardPasses RegisterExamplePass0(
    PassManagerBuilder::EP_EnabledOnOptLevel0, registerExamplePass);
