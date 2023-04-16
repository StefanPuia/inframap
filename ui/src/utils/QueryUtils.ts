import type {QueryObserverIdleResult} from "@sveltestack/svelte-query";

export const whenSuccessful = <T>(consumer: (data: T) => void) => {
  return (result: QueryObserverIdleResult<T>) => {
    if (result.isSuccess) {
      consumer(result.data as any);
    }
  }
}