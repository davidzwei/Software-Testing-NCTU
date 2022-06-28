import angr #the main framework
import claripy #the solver engine

# Create symbolic object
proj = angr.Project("target") # auto_load_libs False for improved performance
sym_arg_size = 30 #Length in Bytes because we will multiply with 8 later
sym_arg = claripy.BVS('target', 8*sym_arg_size)

# Create a state with a symbolic argument
argv = [proj.filename]
argv.append(sym_arg)
state = proj.factory.entry_state(args=argv)

# Generate a simulation_manager
simgr = proj.factory.simulation_manager(state)

# Symbolically execute until we find a state satisfying our find= and avoid= parameters
avoid_addr = [0x400c06, 0x400bc7]
find_addr = 0x401060
simgr.explore(find=find_addr, avoid=avoid_addr)

found = simgr.found[0] # A state that reached the find condition from explore
found.solver.eval(sym_arg, cast_to=bytes) # Return a concrete string value for the sym arg to reach this state
print(found.solver.eval(sym_arg, cast_to=bytes))
